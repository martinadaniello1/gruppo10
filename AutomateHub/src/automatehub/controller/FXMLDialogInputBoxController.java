package automatehub.controller;

import automatehub.model_view.*;
import java.io.File;
import java.net.URL;
import java.time.Duration;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
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

    @FXML
    private VBox vBox;
    private HBox secondBox = new HBox();
    private Label secondLabel = new Label("");
    private TextField secondTextField = new TextField();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        intervalHbox.disableProperty().bind(repetitionBox.selectedProperty().not());
        repetitionLabel.disableProperty().bind(repetitionBox.selectedProperty().not());

        SpinnerValueFactory<Integer> dayValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 365);
        daySpinner.setValueFactory(dayValueFactory);

        SpinnerValueFactory<Integer> hourValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 23);
        hourSpinner.setValueFactory(hourValueFactory);

        SpinnerValueFactory<Integer> minuteValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59);
        minuteSpinner.setValueFactory(minuteValueFactory);

        secondBox.getChildren().addAll(secondLabel, secondTextField);
        secondBox.setMargin(secondLabel, new Insets(0, 10, 10, 0));
        secondBox.setMargin(secondTextField, new Insets(0, 10, 10, 0));

    }

    /**
     * This method sets up the UI according to the type of Action selected.
     *
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
                this.actionLabel.setText("Choose the file you want to copy:");
                addFileChooser(actionBox, FileExtensionFilter.ALL);
                actionTextField.setEditable(false);
                actionTextField.focusTraversableProperty().set(false);
                //Set up the new hbox
                vBox.getChildren().add(3, secondBox);
                secondLabel.setText("Choose the destination directory:");
                addFileChooser(secondBox, FileExtensionFilter.DIRECTORY);
                secondTextField.setEditable(false);
                secondTextField.focusTraversableProperty().set(false);
                break;
            case "Move a file from a directory":
                this.actionLabel.setText("Choose the file you want to move:");
                addFileChooser(actionBox, FileExtensionFilter.ALL);
                actionTextField.setEditable(false);
                actionTextField.focusTraversableProperty().set(false);
                //Set up the new hbox
                vBox.getChildren().add(3, secondBox);
                secondLabel.setText("Choose the destination directory:");
                addFileChooser(secondBox, FileExtensionFilter.DIRECTORY);
                secondTextField.setEditable(false);
                secondTextField.focusTraversableProperty().set(false);
                break;
            case "Append a string at the end of a text file":
                this.actionLabel.setText("Write the text to append:");
                //Set up the new hbox
                vBox.getChildren().add(3, secondBox);
                secondLabel.setText("Choose the text file:");
                addFileChooser(secondBox, FileExtensionFilter.TEXT);
                secondTextField.setEditable(false);
                secondTextField.focusTraversableProperty().set(false);
                break;
            case "Execute external programm":
                this.actionLabel.setText("Choose a programm to run");
                addFileChooser(actionBox, FileExtensionFilter.PYTHON);
                actionTextField.setEditable(false);
                actionTextField.focusTraversableProperty().set(false);
                vBox.getChildren().add(3, secondBox);
                this.secondLabel.setText("Insert arguments");
                secondTextField.setPromptText("Arg1 ; Arg2; ... ; ArgN");
                secondTextField.focusTraversableProperty().set(false);
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
     *
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
        actionTextField.setText(oldRule.getAction().getParam1());
        secondTextField.setText(oldRule.getAction().getParam2());

        Button b = (Button) rulesDialogPane.lookupButton(ButtonType.APPLY);
        b.setDefaultButton(true);
        b.disableProperty().bind(ruleTextField.textProperty().isEmpty().or(actionTextField.textProperty().isEmpty().or(triggerTextField.textProperty().isEmpty())));
        b.setOnAction(event -> editRule(oldRule, triggerTextField.getText(), actionTextField.getText(), ruleTextField.getText(), actionType, triggerType));
        if (!oldRule.getPeriod().isZero()) {
            repetitionBox.setSelected(true);
            Pattern pattern = Pattern.compile("^PT((\\d+)H)?((\\d+)M)?((\\d+)(\\.\\d+)?S)?$");
            Matcher matcher = pattern.matcher(oldRule.getPeriod().toString());

            if (matcher.matches()) {
                int hours = Integer.parseInt(matcher.group(2) != null ? matcher.group(2) : "0");
                int minutes = Integer.parseInt(matcher.group(4) != null ? matcher.group(4) : "0");
                int seconds = (int) Math.round(Double.parseDouble(matcher.group(6) != null ? matcher.group(6) : "0"));

                int totalMinutes = hours * 60 + minutes;
                int days = totalMinutes / (24 * 60);
                int remainingMinutes = totalMinutes % (24 * 60);
                int remainingHours = remainingMinutes / 60;
                int finalMinutes = remainingMinutes % 60;

                daySpinner.getValueFactory().setValue(days);
                hourSpinner.getValueFactory().setValue(remainingHours);
                minuteSpinner.getValueFactory().setValue(finalMinutes);
            }

        }
    }

    private void addFileChooser(HBox box, FileExtensionFilter fileFilter) {
        Button fileChooserButton = new Button("...");
        box.getChildren().add(fileChooserButton);
        TextField tf = findTextFieldInHBox(box);

        if (fileFilter != FileExtensionFilter.DIRECTORY) {
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
                    tf.setText(selectedFile.getAbsolutePath());
                }
            });
        } else {
            fileChooserButton.setOnAction(event -> {
                DirectoryChooser directoryChooser = new DirectoryChooser();
                directoryChooser.setTitle("Choose the directory");

                File selectedFile = directoryChooser.showDialog(box.getScene().getWindow());
                if (selectedFile != null) {
                    tf.setText(selectedFile.getAbsolutePath());
                }
            });
        }
    }

    /**
     * Creates the corresponding trigger when the user selects it.
     *
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
     *
     * @param actionType
     * @return
     */
    private CreatorAction createAction(String actionType) {
        switch (actionType) {
            case "Show a message":
                return new DialogBoxActionCreator(actionTextField.getText());
            case "Play an audio file":
                return new AudioActionCreator(actionTextField.getText());
            case "Append a string at the end of a text file":
                return new AppendToFileActionCreator(actionTextField.getText(), secondTextField.getText());
            case "Copy a file to a directory":
                return new CopyFileActionCreator(actionTextField.getText(), secondTextField.getText());
            case "Move a file from a directory":
                return new MoveFileActionCreator(actionTextField.getText(), secondTextField.getText());
            case "Execute external programm":
                return new ExecutorFileActionCreator(actionTextField.getText(), secondTextField.getText().split(";"));
            default:
                return null;
        }
    }

    /**
     * Creates the Rule. If oldRule is not null, the method calls the editRule
     * of RuleManagerService; otherwise, it calls the addRule.
     *
     * @param ruleName
     * @param actionType
     * @param triggerType
     * @param oldRule
     */
    private void processRule(String ruleName, String actionType, String triggerType, Rule oldRule) {
        CreatorAction action = createAction(actionType);
        CreatorTrigger trigger = createTrigger(triggerType);

        if (!intervalHbox.isDisable()) {

            d = d.plusDays(daySpinner.getValue());
            d = d.plusHours(hourSpinner.getValue());
            d = d.plusMinutes(minuteSpinner.getValue());

            System.out.println(d.toString());
        }
        boolean active;
        if (oldRule==null)
            active =true;
        else active = oldRule.getActive();
        Rule r = new Rule(ruleName, action.create(), trigger.create(), active, d);
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

    private TextField findTextFieldInHBox(HBox hbox) {
        for (Node node : hbox.getChildren()) {
            if (node instanceof TextField) {
                return (TextField) node;
            }
        }
        return null;
    }

}
