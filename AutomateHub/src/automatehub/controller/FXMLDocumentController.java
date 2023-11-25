package automatehub.controller;

import automatehub.model_view.*;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.collections.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.*;
import javafx.util.Callback;

/**
 *
 * @author adc01
 */
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
        //ruleManager.setOnSucceeded();
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
       
                
        activeColumn.setCellFactory(CheckBoxTableCell.forTableColumn(activeColumn));
        
        addButton.disableProperty().bind(triggersBox.valueProperty().isNull().or(actionsBox.valueProperty().isNull()));
            
    }    

    @FXML
    private void addAction(ActionEvent event) {
        Rule provaRule = new Rule("Regola di prova", new DialogBoxAction("Action Prova", "Forza Napoli Sempre"), new TimeTrigger("2023/11/24/16:38","Trigger Prova"), Boolean.TRUE); 
        ruleManager.addRule(provaRule);
    }
   
}
