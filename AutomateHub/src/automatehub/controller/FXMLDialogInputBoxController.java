package automatehub.controller;

import automatehub.model_view.AudioAction;
import automatehub.model_view.DialogBoxAction;
import automatehub.model_view.Rule;
import automatehub.model_view.RuleManagerService;
import automatehub.model_view.TimeTrigger;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


public class FXMLDialogInputBoxController implements Initializable {

    private RuleManagerService ruleManager = RuleManagerService.getRuleManager();
    
    @FXML
    private DialogPane rulesDialogPane;
    @FXML
    private TextField actionNameTextField;
    @FXML
    private Label actionLabel;
    @FXML
    private TextField actionTextField;
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
        if(actionType.equals("Play an audio file")){
            this.actionLabel.setText("Insert the file audio's path:");
            addFileChooser(actionBox);
            actionTextField.setEditable(false);
            actionTextField.focusTraversableProperty().set(false); 
        }
        else if(actionType.equals("Show a message")){
            this.actionLabel.setText("Insert the text to display:");
        }
        
        if(triggerType.equals("When the clock hits ...")){
            this.triggerLabel.setText("Select the time");
            triggerTextField.focusedProperty().addListener((arg0, oldValue, newValue) ->{
            if (!newValue) {
                if(!triggerTextField.getText().matches("^(0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]$")) {
                    triggerTextField.setText("");
            } 
            }
        });
        }
        
        Button b = (Button) rulesDialogPane.lookupButton(ButtonType.APPLY);
        b.setDefaultButton(true);
        b.disableProperty().bind(ruleTextField.textProperty().isEmpty().or(actionTextField.textProperty().isEmpty().or(triggerTextField.textProperty().isEmpty())));
        b.setOnAction(event -> createRule(actionType, triggerType, ruleTextField.getText()));
    }   
    
    public void updateData(String actionType, String triggerType, Rule oldRule){
   
        ruleTextField.setText(oldRule.getNameRule());
        triggerTextField.setText(oldRule.getTrigger().toString());
        actionTextField.setText(oldRule.getAction().toString());
        
        if(actionType.equals("Play an audio file")){
            this.actionLabel.setText("Insert the file audio's path:");
            addFileChooser(actionBox);
            actionTextField.setEditable(false);
        }
        else if(actionType.equals("Show a message")){
            this.actionLabel.setText("Insert the text to display:");
        }
        
        if(triggerType.equals("When the clock hits ...")){
            this.triggerLabel.setText("Select the time");
            triggerTextField.focusedProperty().addListener((arg0, oldValue, newValue) ->{
            if (!newValue) {
                if(!triggerTextField.getText().matches("^(0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]$")) {
                    triggerTextField.setText("");
            } 
            }
        });
        }
        
        Button b = (Button) rulesDialogPane.lookupButton(ButtonType.APPLY);
        b.setDefaultButton(true);
        b.disableProperty().bind(ruleTextField.textProperty().isEmpty().or(actionTextField.textProperty().isEmpty().or(triggerTextField.textProperty().isEmpty())));
        b.setOnAction(event -> editRule(oldRule, triggerTextField.getText(), actionTextField.getText() ,ruleTextField.getText(), actionType, triggerType));
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }    
    
    private void addFileChooser(HBox box){
        Button fileChooserButton = new Button("...");
        box.getChildren().add(fileChooserButton);
        fileChooserButton.setOnAction(event -> { 
            FileChooser fileChooser = new FileChooser(); //apro un fileChooser
            fileChooser.setTitle("Seleziona il file audio"); //definisco il titolo della finestra da cui scegliere il file
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Audio files (*.wav)","*.wav"); //definizion dei filtri per la selezione dei file
            fileChooser.getExtensionFilters().add(extFilter); 
            File f = fileChooser.showOpenDialog(box.getScene().getWindow()); //apro la finestra di dialogo per la scelta del file
            if(f!=null)
                actionTextField.setText(f.getAbsolutePath());
        });
        
        
    }

    private void createRule(String actionType, String triggerType, String ruleName) {
        if(actionType.equals("Play an audio file") && triggerType.equals("When the clock hits ...")){
            AudioAction action = new AudioAction(actionTextField.getText());
            TimeTrigger trigger = new TimeTrigger(triggerTextField.getText());
            Rule r = new Rule(ruleName,action, trigger, true);
            ruleManager.addRule(r);  
        }
        if(actionType.equals("Show a message") && triggerType.equals("When the clock hits ...")){
            DialogBoxAction action = new DialogBoxAction(actionTextField.getText());
            TimeTrigger trigger = new TimeTrigger(triggerTextField.getText());
            Rule r = new Rule(ruleName,action, trigger, true);
            ruleManager.addRule(r);
        }
        
        Stage currentStage = (Stage)rulesDialogPane.getScene().getWindow();
        currentStage.close();
       
    }

    private void editRule(Rule oldRule, String triggerString, String actionString, String ruleName, String actionType, String triggerType) {
        Rule r = null;
        if(actionType.equals("Play an audio file") && triggerType.equals("When the clock hits ...")){
            AudioAction action = new AudioAction(actionTextField.getText());
            TimeTrigger trigger = new TimeTrigger(triggerTextField.getText());
            r = new Rule(ruleName,action, trigger, true);  
        }
        if(actionType.equals("Show a message") && triggerType.equals("When the clock hits ...")){
            DialogBoxAction action = new DialogBoxAction(actionTextField.getText());
            TimeTrigger trigger = new TimeTrigger(triggerTextField.getText());
            r = new Rule(ruleName,action, trigger, true);
        }
        
        ruleManager.editRule(oldRule, r);
        
        Stage currentStage = (Stage)rulesDialogPane.getScene().getWindow();
        currentStage.close();
    }

}
