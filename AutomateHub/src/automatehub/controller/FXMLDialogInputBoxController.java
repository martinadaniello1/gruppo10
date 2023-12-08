package automatehub.controller;

import automatehub.model_view.*;
import static java.lang.Integer.parseInt;
import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.control.*;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.layout.*;
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
    private HBox triggerHBox;
    private HBox dirBox = new HBox();
    private Label dirLabel = new Label("");
    private FoundFileTriggerUI caseFindFile;

    @FXML
    private VBox vBox;
    private HBox secondBox = new HBox();
    private Label secondLabel = new Label("");
    private TextField secondTextField = new TextField();

    private ActionContext context = new ActionContext();
    private TriggerContext triggerContext = new TriggerContext();
    private CreatorTrigger trigger;
    private CreatorAction action;

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

        dirBox.getChildren().addAll(dirLabel);
        dirBox.setMargin(dirLabel, new Insets(0, 10, 15, 0));

    }

    /**
     * This method sets up the UI according to the type of Action selected.
     *
     * @param actionType
     */
    private void setupActionUI(String actionType) {
        ActionMenuText enumType = ActionMenuText.getByMenuText(actionType);
        ActionState state = null;
        switch (enumType) {
            case PLAY:
                state = new AudioActionUI(actionTextField, actionLabel, actionBox);
                break;
            case MEX:
                state = new DialogBoxUI(actionLabel);
                break;
            case COPY:
                state = new CopyFileActionUI(actionTextField, secondTextField, actionLabel, secondLabel, actionBox, secondBox, vBox);
                break;
            case MOVE:
                state = new MoveFileActionUI(actionTextField, secondTextField, actionLabel, secondLabel, actionBox, secondBox, vBox);
                break;
            case APPEND:
                state = new AppendToFileActionUI(secondTextField, actionLabel, secondLabel, secondBox, vBox);
                break;
            case REMOVE:
                state = new RemoveFileActionUI(actionLabel, actionTextField, actionBox);
        }
        context.changeState(state);
        context.setupUI();

    }

    /**
     * This method sets up the UI according to the type of Trigger selected.
     *
     * @param triggerType
     */
    private void setupTriggerUI(String triggerType) {
        //TriggerMenuText enumType = TriggerMenuText.valueOf(triggerType);
        TriggerMenuText enumType = TriggerMenuText.getByMenuText(triggerType);
        TriggerState state = null;

        switch (enumType) {
            case TIME:
                state = new TimeTriggerUI(triggerLabel, triggerTextField);
                break;
            case DAYMONTH:
                state = new DayOfMonthTriggerUI(triggerLabel, triggerTextField);
                break;
            case DAYWEEK:
                state = new DayofWeekTriggerUI(triggerLabel, triggerTextField);
                break;
            case FINDFILE:
                caseFindFile = new FoundFileTriggerUI(triggerLabel, triggerTextField, triggerHBox, dirBox, dirLabel, vBox);
                state = caseFindFile;
                break;
        }
        triggerContext.changeState(state);
        triggerContext.setupUI();
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
            Pattern pattern = Pattern.compile("^PT((\\d+)H)?((\\d+)M)?((\\d+)(\\.\\d+)?S)?$"); //Pattern for the Duration string
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

    /**
     * Creates the corresponding trigger when the user selects it.
     *
     * @param triggerType
     * @return
     */
    private CreatorTrigger createTrigger(String triggerType) {
        TriggerMenuText enumType = TriggerMenuText.getByMenuText(triggerType);

        switch (enumType) {
            case TIME:
                return new TimeTriggerCreator(triggerTextField.getText());
            case DAYWEEK:
                return new DayofWeekTriggerCreator(triggerTextField.getText());
            case FINDFILE:
                return new FoundFileTriggerCreator(triggerTextField.getText(), caseFindFile.getTf());
            case DAYMONTH:
                return new DayOfMonthTriggerCreator(parseInt(triggerTextField.getText()));
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
        ActionMenuText enumType = ActionMenuText.getByMenuText(actionType);
        switch (enumType) {
            case MEX:
                return new DialogBoxActionCreator(actionTextField.getText());
            case PLAY:
                return new AudioActionCreator(actionTextField.getText());
            case APPEND:
                return new AppendToFileActionCreator(actionTextField.getText(), secondTextField.getText());
            case COPY:
                return new CopyFileActionCreator(actionTextField.getText(), secondTextField.getText());
            case MOVE:
                return new MoveFileActionCreator(actionTextField.getText(), secondTextField.getText());
            case REMOVE:
                return new RemoveFileActionCreator(actionTextField.getText());
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
        action = createAction(actionType);
        trigger = createTrigger(triggerType);
        if (!intervalHbox.isDisable()) {

            d = d.plusDays(daySpinner.getValue());
            d = d.plusHours(hourSpinner.getValue());
            d = d.plusMinutes(minuteSpinner.getValue());

            System.out.println(d.toString());
        }
        boolean active;
        if (oldRule == null) {
            active = true;
        } else {
            active = oldRule.getActive();
        }
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
}
