package automatehub.controller;

import automatehub.model_view.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.collections.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.*;
import javafx.stage.Stage;
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
    @FXML
    private TableColumn<Rule, String> nameColumn;
    @FXML
    private TableColumn<Rule, Trigger> triggerColumn;
    @FXML
    private TableColumn<Rule, Action> actionColumn;
    @FXML
    private TableColumn<Rule, Boolean> activeColumn;
    
    
    
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
     
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("nameRule"));
        triggerColumn.setCellValueFactory(new PropertyValueFactory<>("trigger"));
        actionColumn.setCellValueFactory(new PropertyValueFactory<>("action"));
        activeColumn.setCellValueFactory(new PropertyValueFactory<>("active"));
        
        triggerColumn.setCellFactory(column -> {
            return new TableCell<Rule, Trigger>() {
                @Override
                protected void updateItem(Trigger trigger, boolean empty) {
                    super.updateItem(trigger, empty);
                    if (trigger == null || empty) {
                        setText(null);
                        setGraphic(null);
                    } else {
                        setText(trigger.getNameTrigger());
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
                            CheckBox p= new CheckBox();
                            Rule selectedRule = (Rule) getTableRow().getItem();
                            p.setSelected(active);
                            setGraphic(p);
                            p.setOnAction(new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent event) {
                                    selectedRule.setActive(p.isSelected());
                                    System.out.println(selectedRule.getActive() ? "Regola attivata con successo" : "Regola disattivata con successo");
                                }
                            });
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
        //    MenuItem editItem = new MenuItem("Edit");
        //    editItem.setOnAction(new EventHandler<ActionEvent>(){});
            MenuItem removeItem = new MenuItem("Delete");
            removeItem.setOnAction(new EventHandler<ActionEvent>() {
        
                @Override
                public void handle(ActionEvent event) {
                    rulesTable.getItems().remove(row.getItem());
                    rulesList.remove(row.getItem());
                }
            });
            rowMenu.getItems().addAll(/*editItem,*/ removeItem);
            
            // mostrare il menu contestuale solo per le righe che non sono vuote 
            row.contextMenuProperty().bind(
              Bindings.when(row.emptyProperty())
              .then((ContextMenu) null)
              .otherwise(rowMenu));
            return row;
            }
        });   
        
        addButton.disableProperty().bind(triggersBox.valueProperty().isNull().or(actionsBox.valueProperty().isNull()));
        rulesTable.refresh();
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
}
