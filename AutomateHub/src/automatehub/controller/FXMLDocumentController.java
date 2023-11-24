package automatehub.controller;

import automatehub.model_view.*;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.*;

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
    private TableColumn<Rule, String> triggerColumn;
    @FXML
    private TableColumn<Rule, String> actionColumn;
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
    
                
        activeColumn.setCellFactory(CheckBoxTableCell.forTableColumn(activeColumn));
        
        addButton.disableProperty().bind(triggersBox.valueProperty().isNull().or(actionsBox.valueProperty().isNull()));

    }    

    @FXML
    private void addAction(ActionEvent event) {
//        Rule provaRule = new Rule("Regola di prova", new Action(), new TimeTrigger(), Boolean.TRUE); 
//        ruleManager.addRule(provaRule);
        System.out.println("AddAction Excecuted");
    }
   
}
