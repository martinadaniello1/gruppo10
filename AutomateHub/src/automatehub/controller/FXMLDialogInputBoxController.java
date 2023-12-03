package automatehub.controller;

import automatehub.model_view.*;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.stage.DirectoryChooser;
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
    
    private String secondaryInput = "";
     
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }
    
    /**
     * This method sets up the UI according to the type of Action selected.
     * @param actionType 
     */
    private void setupActionUI(String actionType) {
        switch (actionType) {
        case "Play an audio file":
            this.actionLabel.setText("Insert the file audio's path:");
            addFileChooser(actionBox, FileExtensionFilter.WAV);
            actionTextField.setEditable(false);
            actionTextField.focusTraversableProperty().set(false);
            break;
        case "Show a message":
            this.actionLabel.setText("Insert the text to display:");
            break;
        case "Copy a file to a directory":
            this.actionLabel.setText("Choose the file you want to copy and select the destination directory:");
            addFileChooser(actionBox, FileExtensionFilter.ALL);
            addDirectoryChooser(actionBox);
            actionTextField.setEditable(false);
            actionTextField.focusTraversableProperty().set(false);
            break;
        case "Move a file from a directory":
            this.actionLabel.setText("Choose the file you want to move and select the destination directory:");
            addFileChooser(actionBox, FileExtensionFilter.ALL);
            addDirectoryChooser(actionBox);
            actionTextField.setEditable(false);
            actionTextField.focusTraversableProperty().set(false);
            break;   
        
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


    private void addFileChooser(HBox box, FileExtensionFilter fileFilter) {
       Button fileChooserButton = new Button("...");
       box.getChildren().add(fileChooserButton);

       fileChooserButton.setOnAction(event -> {
           FileChooser fileChooser = new FileChooser();
           fileChooser.setTitle("Choose the file");

           if (fileFilter != FileExtensionFilter.ALL) {
               FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
                       fileFilter.getDescription(),
                       fileFilter.getExtension()
               );
               fileChooser.getExtensionFilters().add(extFilter);
           }

           File selectedFile = fileChooser.showOpenDialog(box.getScene().getWindow());

           if (selectedFile != null) {
               actionTextField.setText(selectedFile.getAbsolutePath());
           }
       });
   }
    
    /**
     * Add to the UI the corresponding textfield and button to insert
     * the path of the destination directory
     * @param box 
     */
    private void addDirectoryChooser(HBox box) {
        TextField directoryTextField = new TextField("Pick a directory");
        directoryTextField.setPadding(new Insets(0,10,0,5));
        Button directoryChooserButton = new Button("...");
        //Add the elements to the hbox
        box.getChildren().add(directoryTextField);
        box.getChildren().add(directoryChooserButton);
        //Disable the textField
        directoryTextField.setEditable(false);
        directoryTextField.focusTraversableProperty().set(false);
        
        directoryChooserButton.setOnAction(event -> {
            DirectoryChooser directoryChooser = new DirectoryChooser(); // Usa DirectoryChooser invece di FileChooser
            directoryChooser.setTitle("Seleziona la cartella");
     
            File selectedDirectory = directoryChooser.showDialog(box.getScene().getWindow());

            if (selectedDirectory != null) {
                directoryTextField.setText(selectedDirectory.getAbsolutePath());
                secondaryInput = selectedDirectory.getAbsolutePath();
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
            case "Copy a file to a directory":
                return new CopyFileActionCreator(actionTextField.getText(), secondaryInput);
            case "Move a file from a directory":
                return new MoveFileActionCreator(actionTextField.getText(), secondaryInput);
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
