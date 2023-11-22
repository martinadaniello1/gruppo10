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
    private ChoiceBox<Trigger> triggersBox;
    @FXML
    private ChoiceBox<Action> actionsBox;
    @FXML
    private TableView<Rule> rulesTable;
    @FXML
    private TableColumn<Rule, String> rulesColumn;
    @FXML
    private TableColumn<Rule, Boolean> disableColumn;
    
    private ObservableList<Rule> rulesList;
    
 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        rulesList = FXCollections.observableArrayList();
        
        rulesTable.setItems(rulesList);
        
//        disableColumn.setCellValueFactory(cellData -> cellData.getValue().selectedProperty());
        
        disableColumn.setCellFactory(CheckBoxTableCell.forTableColumn(disableColumn));
        
        addButton.disableProperty().bind(triggersBox.valueProperty().isNull().or(actionsBox.valueProperty().isNull()));

    }    

    @FXML
    private void addAction(ActionEvent event) {
        

    }
    
}
