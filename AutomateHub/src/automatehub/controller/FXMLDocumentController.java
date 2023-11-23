/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
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
    @FXML
    private TableColumn<Rule, String> rulesColumn;
    @FXML
    private TableColumn<Rule, Boolean> disableColumn;
    
    private ObservableList<Rule> rulesList;
    private ObservableList<String> actionsList;
    private ObservableList<String> triggersList;
    
 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        rulesList = FXCollections.observableArrayList();
        actionsList = FXCollections.observableArrayList();
        triggersList = FXCollections.observableArrayList();
        
        actionsList.addAll("Play an audio file", "Show a message");
        triggersList.addAll("When the clock hits ...");
        actionsBox.setItems(actionsList);
        triggersBox.setItems(triggersList);
  
        
        rulesTable.setItems(rulesList);
        
        
        
//        disableColumn.setCellValueFactory(cellData -> cellData.getValue().selectedProperty());
        
        disableColumn.setCellFactory(CheckBoxTableCell.forTableColumn(disableColumn));
        
        addButton.disableProperty().bind(triggersBox.valueProperty().isNull().or(actionsBox.valueProperty().isNull()));

    }    

    @FXML
    private void addAction(ActionEvent event) {
        

    }
   
}
