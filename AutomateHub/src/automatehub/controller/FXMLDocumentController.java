package automatehub.controller;

import automatehub.model_view.*;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.collections.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.*;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Callback;

public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Button addButton;
    @FXML
    private ChoiceBox<String> triggersBox;
    @FXML
    private ChoiceBox<String> actionsBox;
    @FXML
    private TableView<Rule> rulesTable;
    
    private RuleManagerService ruleManager = RuleManagerService.getRuleManager();
    
    private ObservableList<Rule> rulesList = ruleManager.getRuleList();
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
    
    
    
    public void startAction(){
        ruleManager.start();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        actionsList = FXCollections.observableArrayList();
        triggersList = FXCollections.observableArrayList();
        
        actionsList.addAll("Play an audio file", "Show a message");
        triggersList.addAll("When the clock hits ...");
        actionsBox.setItems(actionsList);
        triggersBox.setItems(triggersList);
        
        rulesTable.setItems(rulesList);
        rulesTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        selectedRules=rulesTable.getSelectionModel().getSelectedItems(); //memorizzo in tale array la riga selezionata dall'utente
        
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("nameRule"));
        triggerColumn.setCellValueFactory(new PropertyValueFactory<>("trigger"));
        actionColumn.setCellValueFactory(new PropertyValueFactory<>("action"));
        activeColumn.setCellValueFactory(new PropertyValueFactory<>("active"));
        
       
    /*    triggerColumn.setCellFactory(column -> {
            return new TableCell<Rule, Trigger>() {
                @Override
                protected void updateItem(Trigger trigger, boolean empty) {
                    super.updateItem(trigger, empty);
                    if (trigger == null || empty) {
                        setText(null);
                        setGraphic(null);
                    } else {
                        setText(trigger.toString());
                    }
                }
            };
        });
        
        actionColumn.setCellFactory(column -> {
            return new TableCell<Rule, Action>() {
                @Override
                protected void updateItem(Action action, boolean empty) {
                    super.updateItem(action, empty);
                    if (action == null || empty) {
                        setText(null);
                        setGraphic(null);
                    } else {
                        setText(action.getNameAction());
                    }
                }
            };
        });
        */
        activeColumn.setCellFactory(new Callback<TableColumn<Rule, Boolean>, TableCell<Rule, Boolean>>() {
            @Override
            public TableCell<Rule, Boolean> call(TableColumn<Rule, Boolean> column) {
                return new CheckBoxTableCell <Rule, Boolean> (){
                     
                    @Override 
                    public void updateItem(Boolean active, boolean empty) {
                        super.updateItem(active, empty);
                        
                        if(empty || active == null) {
                            setGraphic(null);
                            setText(null);
                        }
                        else {
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
            
        });
        
        //Definisco l'interazione con il tasto destro sulle righe della tabella
        rulesTable.setRowFactory(
        new Callback<TableView<Rule>, TableRow<Rule>>() {
            @Override
            public TableRow<Rule> call(TableView<Rule> tableView) {
            final TableRow<Rule> row = new TableRow<>();
            final ContextMenu rowMenu = new ContextMenu();
            MenuItem editItem = new MenuItem("Edit");
            editItem.setOnAction(new EventHandler<ActionEvent>(){
                
                @Override
                public void handle(ActionEvent event) {
                    //rulesTable.getItems().edit(row.getItem()) ;
                }
            });
            MenuItem removeItem = new MenuItem("Delete");
            removeItem.setOnAction(new EventHandler<ActionEvent>() {
        
                @Override
                public void handle(ActionEvent event) {
                    removeAction();
                }
            });
            rowMenu.getItems().addAll(editItem, removeItem);
            
            // mostrare il menu contestuale solo per le righe che non sono vuote 
            row.contextMenuProperty().bind(
              Bindings.when(row.emptyProperty())
              .then((ContextMenu) null)
              .otherwise(rowMenu));
            return row;
            }
        });   
        
        addButton.disableProperty().bind(triggersBox.valueProperty().isNull().or(actionsBox.valueProperty().isNull()));
        removeButton.disableProperty().bind(Bindings.createBooleanBinding(() -> selectedRules.isEmpty(),selectedRules));
        
    }    

    @FXML
    private void addAction(ActionEvent event) throws IOException  {
        // carica il nuovo FXML
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/automatehub/model_view/FXMLDialogInputBox.fxml"));
        Parent nuovoRoot = loader.load();

        // carica il controller del nuovo FXML
        FXMLDialogInputBoxController  nuovoController = loader.getController();

        // inizializza parametri
        nuovoController.initData(actionsBox.getValue(),triggersBox.getValue());
        
        // Crea una nuova finestra per il nuovo FXML
        Stage nuovoStage = new Stage();
        Scene nuovoScene = new Scene(nuovoRoot);
        nuovoStage.setScene(nuovoScene);
        
        // Mostra la nuova finestra
        nuovoStage.show();
        
    }   

    @FXML
    private void removeAction() {
        System.out.println(selectedRules.toString()); // Log
        Alert confirmRemoval = new Alert(Alert.AlertType.CONFIRMATION);
        confirmRemoval.setTitle("Messaggio");
        confirmRemoval.setHeaderText(null);
        confirmRemoval.setContentText("Sei sicuro di voler cancellare le regole selezionate?");
        Optional<ButtonType> result = confirmRemoval.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            ArrayList<Rule> rulesToRemove = new ArrayList<>(selectedRules);
            for (Rule rule : rulesToRemove) {
                try {
                    rule.toString();
                    ruleManager.removeRule(rule); // Rimuovi gli elementi selezionati
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }


    
    public void handleOpenRequest(WindowEvent event)  {
        try{ 
            // Importa le regole quando il programma viene avviato
            ruleManager.importRule();

        }catch (IOException exc) {
            System.out.printf("IOException: "+exc);
        }
    }
    
    public void handleCloseRequest(WindowEvent event)  {
        try{ 
            // Salva le regole quando il programma viene chiuso
            ruleManager.exportRule();

        }catch (IOException exc) {
            System.out.printf("IOException: "+exc);
        }   
        // Chiudi l'applicazione
        Platform.exit();
    }
    
    @FXML
    private void editAction(){
        
    }   
    
}
