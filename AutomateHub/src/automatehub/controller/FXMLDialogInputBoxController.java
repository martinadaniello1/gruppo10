package automatehub.controller;

import automatehub.model_view.*;
import java.io.File;
import java.net.URL;
import java.time.Duration;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class FXMLDialogInputBoxController implements Initializable {

    private RuleManagerService ruleManager = RuleManagerService.getRuleManager();

    @FXML
    private DialogPane rulesDialogPane;
    @FXML
    private Label actionLabel;
    @FXML
    private TextField actionTextField;
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
    @FXML
    private Label repetitionLabel;
    @FXML
    private CheckBox repetitionBox;
    
    private Duration d = Duration.ZERO;
    @FXML
    private HBox intervalHbox;
    @FXML
    private Spinner<Integer> daySpinner;
    @FXML
    private Spinner<Integer> hourSpinner;
    @FXML
    private Spinner<Integer> minuteSpinner;
      

    
     
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        intervalHbox.disableProperty().bind(repetitionBox.selectedProperty().not());
        repetitionLabel.disableProperty().bind(repetitionBox.selectedProperty().not());
        
        SpinnerValueFactory<Integer> dayValueFactory= new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 365);
        daySpinner.setValueFactory(dayValueFactory);
        
        SpinnerValueFactory<Integer> hourValueFactory= new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 23);
        hourSpinner.setValueFactory(hourValueFactory);
        
        SpinnerValueFactory<Integer> minuteValueFactory= new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59);
        minuteSpinner.setValueFactory(minuteValueFactory);
    }

    
    
    /**
     * This method sets up the UI according to the type of Action selected.
     * @param actionType 
     */
    private void setupActionUI(String actionType) {
        if (actionType.equals("Play an audio file")) {
            this.actionLabel.setText("Insert the file audio's path:");
            addFileChooser(actionBox);
            actionTextField.setEditable(false);
            actionTextField.focusTraversableProperty().set(false);
        } else if (actionType.equals("Show a message")) {
            this.actionLabel.setText("Insert the text to display:");
        }
    }

    /**
     * This method checks user's input when typing the hour of the day.
     */
    private void setupTimeValidation() {
        triggerTextField.focusedProperty().addListener((arg0, oldValue, newValue) -> {
            if (!newValue) {
                if (!triggerTextField.getText().matches("^(0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]$")) {
                    triggerTextField.setText("");
                }
            }
        });
        
        intervalHbox.disableProperty().bind(repetitionBox.selectedProperty().not());
        repetitionLabel.disableProperty().bind(repetitionBox.selectedProperty().not());
        
        SpinnerValueFactory<Integer> dayValueFactory= new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 365);
        daySpinner.setValueFactory(dayValueFactory);
        
        SpinnerValueFactory<Integer> hourValueFactory= new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 23);
        hourSpinner.setValueFactory(hourValueFactory);
        
        SpinnerValueFactory<Integer> minuteValueFactory= new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59);
        minuteSpinner.setValueFactory(minuteValueFactory);
    }    
    
   

    /**
     * This method sets up the UI according to the type of Trigger selected.
     * @param triggerType 
     */
    private void setupTriggerUI(String triggerType) {
        if (triggerType.equals("When the clock hits ...")) {
            this.triggerLabel.setText("Select the time");
            setupTimeValidation();
        }
    }

    public void initData(String actionType, String triggerType) {
        setupActionUI(actionType);
        setupTriggerUI(triggerType);

        Button b = (Button) rulesDialogPane.lookupButton(ButtonType.APPLY);
        b.setDefaultButton(true);
        b.disableProperty().bind(ruleTextField.textProperty().isEmpty().or(actionTextField.textProperty().isEmpty().or(triggerTextField.textProperty().isEmpty())));
        b.setOnAction(event -> createRule(actionType, triggerType, ruleTextField.getText()));
    }

    public void updateData(String actionType, String triggerType, Rule oldRule) {
        setupActionUI(actionType);
        setupTriggerUI(triggerType);

        ruleTextField.setText(oldRule.getNameRule());
        triggerTextField.setText(oldRule.getTrigger().toString());
        actionTextField.setText(oldRule.getAction().toString());

        Button b = (Button) rulesDialogPane.lookupButton(ButtonType.APPLY);
        b.setDefaultButton(true);
        b.disableProperty().bind(ruleTextField.textProperty().isEmpty().or(actionTextField.textProperty().isEmpty().or(triggerTextField.textProperty().isEmpty())));
        b.setOnAction(event -> editRule(oldRule, triggerTextField.getText(), actionTextField.getText(), ruleTextField.getText(), actionType, triggerType));
    }


    private void addFileChooser(HBox box) {
        Button fileChooserButton = new Button("...");
        box.getChildren().add(fileChooserButton);
        fileChooserButton.setOnAction(event -> {
            FileChooser fileChooser = new FileChooser(); //Create a FileChooser
            fileChooser.setTitle("Choose the audio file"); 
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Audio files (*.wav)", "*.wav"); //Filters definition for the file extension
            fileChooser.getExtensionFilters().add(extFilter);
            File f = fileChooser.showOpenDialog(box.getScene().getWindow()); //Open the dialog to choose the file
            if (f != null) {
                actionTextField.setText(f.getAbsolutePath());
            }
        });

    }

    /**
     * Creates the corresponding trigger when the user selects it.
     * @param triggerType
     * @return 
     */
    private CreatorTrigger createTrigger(String triggerType) {
        switch (triggerType) {
            case "When the clock hits ...":
                return new TimeTriggerCreator(triggerTextField.getText());
            default:
                return null; 
        }
    }

    /**
     * Creates the corresponding action when the user selects it.
     * @param actionType
     * @return 
     */
    private CreatorAction createAction(String actionType) {
        switch (actionType) {
            case "Show a message":
                return new DialogBoxActionCreator(actionTextField.getText());
            case "Play an audio file":
                return new AudioActionCreator(actionTextField.getText());
            default:
                return null; 
            }
    }

    /**
     * Creates the Rule. If oldRule is not null, the method calls the editRule of RuleManagerService; otherwise, it calls the addRule.
     * @param ruleName
     * @param actionType
     * @param triggerType
     * @param oldRule 
     */
    private void processRule(String ruleName, String actionType, String triggerType, Rule oldRule) {
        CreatorAction action = createAction(actionType);
        CreatorTrigger trigger = createTrigger(triggerType);
        
        if(!intervalHbox.isDisable()) {
                
                d= d.plusDays(daySpinner.getValue());
                d= d.plusHours(hourSpinner.getValue());
                d= d.plusMinutes(minuteSpinner.getValue());
                
                System.out.println(d.toString());
            }

        Rule r = new Rule(ruleName, action.create(), trigger.create(), true,d);
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


