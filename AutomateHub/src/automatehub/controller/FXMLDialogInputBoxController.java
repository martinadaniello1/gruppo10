/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package automatehub.controller;

import automatehub.model_view.AudioAction;
import automatehub.model_view.DialogBoxAction;
import automatehub.model_view.Rule;
import automatehub.model_view.RuleManagerService;
import automatehub.model_view.TimeTrigger;
import java.io.File;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author adc01
 */
public class FXMLDialogInputBoxController implements Initializable {

    private RuleManagerService ruleManager = RuleManagerService.getRuleManager();
    
    @FXML
    private DialogPane rulesDialogPane;
    @FXML
    private Label actionNameLabel;
    @FXML
    private TextField actionNameTextField;
    @FXML
    private Label actionLabel;
    @FXML
    private TextField actionTextField;
    @FXML
    private Label triggerNameLabel;
    @FXML
    private TextField triggerNameTextField;
    @FXML
    private Label triggerLabel;
    @FXML
    private TextField triggerTextField;
    @FXML
    private HBox actionBox;
    @FXML
    private Label ruleNameLabel;
    @FXML
    private TextField ruleTextField;
        
    public void initData(String actionType, String triggerType){
        if(actionType.equals("Play an audio file"))
            this.actionLabel.setText("Insert the file audio's path:");
        else if(actionType.equals("Show a message"))
            this.actionLabel.setText("Insert the text to display:");
        
        if(triggerType.equals("When the clock hits ..."))
            this.triggerLabel.setText("Select the time");
        
        if(actionType.equals("Play an audio file"))
            addFileChooser(actionBox);
         
        Button b = (Button) rulesDialogPane.lookupButton(ButtonType.APPLY);
        b.disableProperty().bind(ruleTextField.textProperty().isEmpty().or(actionNameTextField.textProperty().isEmpty().or(actionTextField.textProperty().isEmpty().or(triggerNameTextField.textProperty().isEmpty().or(triggerTextField.textProperty().isEmpty())))));
        b.setOnAction(event -> createRule(actionType, triggerType, ruleTextField.getText()));
    }   
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        triggerTextField.focusedProperty().addListener((arg0, oldValue, newValue) ->{
            if (!newValue) {
                if(!triggerTextField.getText().matches("^(0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]$")) {
                    triggerTextField.setText("");
            } 
            }
        });
    }    
    
    private void addFileChooser(HBox box){
        Button fileChooserButton = new Button("...");
        box.getChildren().add(fileChooserButton);
        fileChooserButton.setOnAction(event -> { 
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Seleziona il file audio");
            File f = fileChooser.showOpenDialog(box.getScene().getWindow());
            if(f!=null)
                actionTextField.setText(f.getAbsolutePath());
        });
        
        
    }

    private void createRule(String actionType, String triggerType, String ruleName) {
        if(actionType.equals("Play an audio file") && triggerType.equals("When the clock hits ...")){
            AudioAction action = new AudioAction(actionNameTextField.getText(), actionTextField.getText());
            TimeTrigger trigger = new TimeTrigger(triggerTextField.getText(), triggerNameTextField.getText());
            Rule r = new Rule(ruleName,action, trigger, true);
            ruleManager.addRule(r);  
        }
        if(actionType.equals("Show a message") && triggerType.equals("When the clock hits ...")){
            DialogBoxAction action = new DialogBoxAction(actionNameTextField.getText(), actionTextField.getText());
            TimeTrigger trigger = new TimeTrigger(triggerTextField.getText(), triggerNameTextField.getText());
            Rule r = new Rule(ruleName,action, trigger, true);
            ruleManager.addRule(r);
        }
        
        Stage currentStage = (Stage)rulesDialogPane.getScene().getWindow();
        currentStage.close();
       
    }

    
}
