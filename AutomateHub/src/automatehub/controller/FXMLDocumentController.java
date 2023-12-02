package automatehub.controller;

import automatehub.model_view.*;
import java.io.IOException;
import java.net.URL;
import javafx.collections.*;
import java.util.*;
import java.util.logging.*;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.event.*;
import javafx.fxml.*;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.*;

public class FXMLDocumentController implements Initializable, RuleObserver {

    @FXML
    private Button addButton;
    @FXML
    private ChoiceBox<String> triggersBox;
    @FXML
    private ChoiceBox<String> actionsBox;
    @FXML
    private TableView<Rule> rulesTable;

    private RuleManagerService ruleManager = RuleManagerService.getRuleManager();

    private ObservableList<Rule> rulesList = FXCollections.observableArrayList();
    private ObservableList<String> actionsList;
    private ObservableList<String> triggersList;
    private ObservableList<Rule> selectedRules;
    @FXML
    private TableColumn<Rule, String> nameColumn;
    @FXML
    private TableColumn<Rule, Trigger> triggerColumn;
    @FXML
    private TableColumn<Rule, Action> actionColumn;
    @FXML
    private TableColumn<Rule, Boolean> activeColumn;
    @FXML
    private Button removeButton;
    @FXML
    private Button editButton;

    /**
     * Starts the RuleManagerService.
     */
    public void startAction() {
        ruleManager.start();
    }

    /**
     * Initializes all the UI elements of the window.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ruleManager.addObserver(this);
        //Setting up the choice boxes for the types of triggers and actions.
        actionsList = FXCollections.observableArrayList();
        triggersList = FXCollections.observableArrayList();

        actionsList.addAll("Play an audio file", "Show a message");
        triggersList.addAll("When the clock hits ...");
        actionsBox.setItems(actionsList);
        triggersBox.setItems(triggersList);

        //Setting up the TableView that contains all the rules, in order to show them.
        rulesTable.setItems(rulesList);
        rulesTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        selectedRules = rulesTable.getSelectionModel().getSelectedItems(); //The selectedRules ArrayList contains all the rows selected by the user.

        nameColumn.setCellValueFactory(new PropertyValueFactory<>("nameRule"));
        triggerColumn.setCellValueFactory(new PropertyValueFactory<>("trigger"));
        actionColumn.setCellValueFactory(new PropertyValueFactory<>("action"));
        activeColumn.setCellValueFactory(new PropertyValueFactory<>("active"));
        rulesTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                // When a row is selected, set the choice boxes to the rule's action type/trigger type.
                actionsBox.setValue(newValue.getAction().getType());
                triggersBox.setValue(newValue.getTrigger().getType());
            }
        });
        //Set the active column with a checkbox in each cell.
        activeColumn.setCellFactory(new Callback<TableColumn<Rule, Boolean>, TableCell<Rule, Boolean>>() {
            @Override
            public TableCell<Rule, Boolean> call(TableColumn<Rule, Boolean> column) {
                return new CheckBoxTableCell<Rule, Boolean>() {

                    @Override
                    public void updateItem(Boolean active, boolean empty) {
                        super.updateItem(active, empty);

                        if (empty || active == null) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            CheckBox p = new CheckBox();
                            Rule selectedRule = (Rule) getTableRow().getItem();
                            if (selectedRule != null && selectedRule.activeProperty() != null) {
                                p.selectedProperty().bindBidirectional(selectedRule.activeProperty());
                                setGraphic(p);
                            }
                        }
                    }
                };
            }
        ;

        });

        /*Defining the ContextMenu with two buttons: Edit and Delete, which can be opened by the user 
        clicking the right button of the mouse on the table's rows.
        The Edit action is visualized only when the user selects one row; the Delete action 
        is visualized when the user selects multiple rows as well.*/
        rulesTable.setRowFactory(
                new Callback<TableView<Rule>, TableRow<Rule>>() {
            @Override
            public TableRow<Rule> call(TableView<Rule> tableView) {
                final TableRow<Rule> row = new TableRow<>();

                // Context Menu for a single row
                final ContextMenu singleRowMenu = new ContextMenu();
                MenuItem editItem = new MenuItem("Edit");
                editItem.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        try {
                            editAction();
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }
                });
                MenuItem removeItem = new MenuItem("Delete");
                removeItem.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        removeAction();
                    }
                });
                singleRowMenu.getItems().addAll(editItem, removeItem);

                // ContextMenu for multiple rows
                final ContextMenu multipleRowsMenu = new ContextMenu();
                MenuItem removeMultipleItem = new MenuItem("Delete");
                removeMultipleItem.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        removeAction();
                    }
                });
                multipleRowsMenu.getItems().add(removeMultipleItem);

                //Show the ContextMenu only for non empty rows.
                row.contextMenuProperty().bind(
                        Bindings.when(row.emptyProperty())
                                .then((ContextMenu) null)
                                .otherwise(Bindings.when(Bindings.size(selectedRules).isEqualTo(1))
                                        .then(singleRowMenu)
                                        .otherwise(multipleRowsMenu)
                                )
                );
                return row;
            }
        }
        );

        /*Setting up the buttons' bindings properly. The Add Button is enabled only when the user 
        selects both a trigger and an action; the Remove Button is enabled when the user selects
        at least a row from the table; the edit Button is enabled when the user selects only one row.
         */
        addButton.disableProperty().bind(triggersBox.valueProperty().isNull().or(actionsBox.valueProperty().isNull()));
        removeButton.disableProperty().bind(Bindings.createBooleanBinding(() -> selectedRules.isEmpty(), selectedRules));
        editButton.disableProperty().bind(Bindings.createBooleanBinding(() -> selectedRules.size() != 1, selectedRules));
        editButton.setOnAction((ActionEvent event) -> {
            try {
                editAction();
            } catch (IOException ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    @FXML
    private void addAction(ActionEvent event) throws IOException {
        //Load the new FXML
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/automatehub/model_view/FXMLDialogInputBox.fxml"));
        Parent nuovoRoot = loader.load();

        //Load the corresponding controller 
        FXMLDialogInputBoxController nuovoController = loader.getController();

        //Pass the trigger and action types to the new controller
        nuovoController.initData(actionsBox.getValue(), triggersBox.getValue());

        // Create a new window
        Stage nuovoStage = new Stage();
        Scene nuovoScene = new Scene(nuovoRoot);
        nuovoStage.setScene(nuovoScene);

        // Show it
        nuovoStage.show();

    }

    @FXML
    private void removeAction() {
        System.out.println(selectedRules.toString()); // Log
        Alert confirmRemoval = new Alert(Alert.AlertType.CONFIRMATION);
        confirmRemoval.setTitle("Alert");
        confirmRemoval.setHeaderText(null);
        confirmRemoval.setContentText("Are you sure you want to delete the selected rules?");
        Optional<ButtonType> result = confirmRemoval.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            ArrayList<Rule> rulesToRemove = new ArrayList<>(selectedRules);
            for (Rule rule : rulesToRemove) {
                try {
                    rule.toString();
                    ruleManager.removeRule(rule); // Remove the selected items
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void handleOpenRequest(WindowEvent event) throws ClassNotFoundException {
        try {
            // Import the rules when the application is opened
            ruleManager.importRule();

        } catch (IOException exc) {
            System.out.printf("IOException: " + exc);
        }
    }

    public void handleCloseRequest(WindowEvent event) {
        try {
            // Save the rules when the application is closed
            ruleManager.exportRule();
            ruleManager.stop();

        } catch (IOException exc) {
            System.out.printf("IOException: " + exc);
        }
        // Close the application

        Platform.exit();
    }

    private void editAction() throws IOException {
        //Load the new FXML
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/automatehub/model_view/FXMLDialogInputBox.fxml"));
        Parent nuovoRoot = loader.load();

        // load the controller
        FXMLDialogInputBoxController nuovoController = loader.getController();

        // pass the action and trigger types and the selected rule to edit
        nuovoController.updateData(actionsBox.getValue(), triggersBox.getValue(), selectedRules.get(0));

        // Create a new window for this controller
        Stage nuovoStage = new Stage();
        Scene nuovoScene = new Scene(nuovoRoot);
        nuovoStage.setScene(nuovoScene);

        // Show it
        nuovoStage.show();
    }

    @Override
    public void onRuleAdded(Rule rule) {
        // Aggiorna la TableView quando una nuova regola viene aggiunta
        rulesList.add(rule);
    }

    @Override
    public void onRuleRemoved(Rule rule) {
        // Aggiorna la TableView quando una regola viene rimossa
        rulesList.remove(rule);
    }

    @Override
    public void onRuleEdited(Rule oldRule, Rule newRule) {
        // Aggiorna la TableView quando una regola viene modificata
        rulesList.remove(oldRule);
        rulesList.add(newRule);
    }

    @Override
    public void onRuleVerified(Rule rule) {
        Platform.runLater(() -> {
            System.out.println("regola verificata con esito positivo:" + rule.toString()); //Logging            
            rule.getAction().execute();
            onActionExecuted(rule);
            rule.setActive(false);
        });
    }

    @Override
    public void onActionExecuted(Rule rule) {
        switch (rule.getAction().getType()) {
            case "Show a message":
                DialogBoxAction action = (DialogBoxAction) rule.getAction();
                if (action.getMessage() != null) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Message");
                    alert.setHeaderText(null);
                    alert.setContentText(action.getMessage());
                    alert.getButtonTypes().setAll(ButtonType.OK);
                    // Show the dialog box 
                    alert.show();
                }
                break;
            case "Play an audio file":
                AudioAction audioAction = (AudioAction) rule.getAction();
                VBox root = new VBox();
                Scene scene = new Scene(root, 128, 128);
                Button btn = new Button();
                btn.setText("Stop Sound");
                btn.setOnAction(new EventHandler<ActionEvent>() {

                    @Override
                    public void handle(ActionEvent event) {
                        audioAction.stopPlaying();
                    }
                });
                root.setAlignment(Pos.CENTER); // Center aligns its children
                root.setSpacing(10); // You can adjust the spacing as needed
                root.getChildren().add(btn);
                Stage s = new Stage();
                s.setScene(scene);
                s.setOnCloseRequest(e -> {
                    // Interrompi la riproduzione audio quando lo stage viene chiuso
                    audioAction.stopPlaying();
                });
                s.show();

        }
    }

}
