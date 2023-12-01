package automatehub.controller;

import automatehub.model_view.Action;
import automatehub.model_view.AudioAction;
import automatehub.model_view.AudioActionCreator;
import automatehub.model_view.CreatorAction;
import automatehub.model_view.CreatorTrigger;
import automatehub.model_view.DialogBoxAction;
import automatehub.model_view.DialogBoxActionCreator;
import automatehub.model_view.Rule;
import automatehub.model_view.RuleManagerService;
import automatehub.model_view.TimeTrigger;
import automatehub.model_view.TimeTriggerCreator;
import automatehub.model_view.Trigger;
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

    public void initData(String actionType, String triggerType) {
        if (actionType.equals("Play an audio file")) {
            this.actionLabel.setText("Insert the file audio's path:");
            addFileChooser(actionBox);
            actionTextField.setEditable(false);
            actionTextField.focusTraversableProperty().set(false);
        } else if (actionType.equals("Show a message")) {
            this.actionLabel.setText("Insert the text to display:");
        }

        if (triggerType.equals("When the clock hits ...")) {
            this.triggerLabel.setText("Select the time");
            triggerTextField.focusedProperty().addListener((arg0, oldValue, newValue) -> {
                if (!newValue) {
                    if (!triggerTextField.getText().matches("^(0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]$")) {
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

    public void updateData(String actionType, String triggerType, Rule oldRule) {

        ruleTextField.setText(oldRule.getNameRule());
        triggerTextField.setText(oldRule.getTrigger().toString());
        actionTextField.setText(oldRule.getAction().toString());

        if (actionType.equals("AudioAction")) {
            this.actionLabel.setText("Insert the file audio's path:");
            addFileChooser(actionBox);
            actionTextField.setEditable(false);
        } else if (actionType.equals("DialogBoxAction")) {
            this.actionLabel.setText("Insert the text to display:");
        }

        if (triggerType.equals("TimeTrigger")) {
            this.triggerLabel.setText("Select the time");
            triggerTextField.focusedProperty().addListener((arg0, oldValue, newValue) -> {
                if (!newValue) {
                    if (!triggerTextField.getText().matches("^(0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]$")) {
                        triggerTextField.setText("");
                    }
                }
            });
        }

        Button b = (Button) rulesDialogPane.lookupButton(ButtonType.APPLY);
        b.setDefaultButton(true);
        b.disableProperty().bind(ruleTextField.textProperty().isEmpty().or(actionTextField.textProperty().isEmpty().or(triggerTextField.textProperty().isEmpty())));
        b.setOnAction(event -> editRule(oldRule, triggerTextField.getText(), actionTextField.getText(), ruleTextField.getText(), actionType, triggerType));
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    private void addFileChooser(HBox box) {
        Button fileChooserButton = new Button("...");
        box.getChildren().add(fileChooserButton);
        fileChooserButton.setOnAction(event -> {
            FileChooser fileChooser = new FileChooser(); //apro un fileChooser
            fileChooser.setTitle("Seleziona il file audio"); //definisco il titolo della finestra da cui scegliere il file
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Audio files (*.wav)", "*.wav"); //definizion dei filtri per la selezione dei file
            fileChooser.getExtensionFilters().add(extFilter);
            File f = fileChooser.showOpenDialog(box.getScene().getWindow()); //apro la finestra di dialogo per la scelta del file
            if (f != null) {
                actionTextField.setText(f.getAbsolutePath());
            }
        });

    }

    /*  private void createRule(String actionType, String triggerType, String ruleName) {
        CreatorAction action=null;
        CreatorTrigger trigger=null;
        switch (triggerType){
            case ("When the clock hits ..."):
                trigger = new TimeTriggerCreator(triggerTextField.getText());
                break;
        }
        
        switch (actionType){
            case ("Show a message"):
                 action = (DialogBoxActionCreator) new DialogBoxActionCreator(actionTextField.getText());
                break;
            case "Play an audio file":
                 action = new AudioActionCreator(actionTextField.getText());
                 break;
        }
        
        Rule r = new Rule(ruleName,action.create(), trigger.create(), true);
        ruleManager.addRule(r);
        Stage currentStage = (Stage)rulesDialogPane.getScene().getWindow();
        currentStage.close();
       
    }

    private void editRule(Rule oldRule, String triggerString, String actionString, String ruleName, String actionType, String triggerType) {
        CreatorAction action=null;
        CreatorTrigger trigger=null;
        switch (triggerType){
            case ("When the clock hits ..."):
                trigger = new TimeTriggerCreator(triggerTextField.getText());
                break;
        }
        
        switch (actionType){
            case ("Show a message"):
                 action = (DialogBoxActionCreator) new DialogBoxActionCreator(actionTextField.getText());
                break;
            case "Play an audio file":
                 action = new AudioActionCreator(actionTextField.getText());
                 break;
        }
        
        Rule r = new Rule(ruleName,action.create(), trigger.create(), true);
        ruleManager.editRule(oldRule, r);
        
        Stage currentStage = (Stage)rulesDialogPane.getScene().getWindow();
        currentStage.close();
    }
     */
    private CreatorTrigger createTrigger(String triggerType) {
        switch (triggerType) {
            case "When the clock hits ...":
                return new TimeTriggerCreator(triggerTextField.getText());
            default:
                return null; // Gestisci il caso di default o lancia un'eccezione
        }
    }

    private CreatorAction createAction(String actionType) {
        switch (actionType) {
            case "Show a message":
                return new DialogBoxActionCreator(actionTextField.getText());
            case "Play an audio file":
                return new AudioActionCreator(actionTextField.getText());
            default:
                return null; // Gestisci il caso di default o lancia un'eccezione
            }
    }

    private void processRule(String ruleName, String actionType, String triggerType, Rule oldRule) {
        CreatorAction action = createAction(actionType);
        CreatorTrigger trigger = createTrigger(triggerType);

        Rule r = new Rule(ruleName, action.create(), trigger.create(), true);
        if (oldRule != null) {
            ruleManager.editRule(oldRule, r);
        } else {
            ruleManager.addRule(r);
        }

        Stage currentStage = (Stage) rulesDialogPane.getScene().getWindow();
        currentStage.close();
    }

    private void createRule(String actionType, String triggerType, String ruleName) {
        processRule(ruleName, actionType, triggerType, null);
    }

    private void editRule(Rule oldRule, String triggerString, String actionString, String ruleName, String actionType, String triggerType) {
        processRule(ruleName, actionType, triggerType, oldRule);
    }

}
