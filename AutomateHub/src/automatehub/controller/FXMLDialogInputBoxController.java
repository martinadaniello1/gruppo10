package automatehub.controller;

import automatehub.controller.trigger_state.*;
import automatehub.controller.action_state.*;
import automatehub.model_view.trigger.*;
import automatehub.model_view.trigger.creator.*;
import automatehub.model_view.action.creator.*;
import automatehub.model_view.action.*;
import automatehub.model_view.*;
import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;
import java.net.URL;
import java.time.*;
import java.time.format.DateTimeFormatter;
import javafx.scene.control.*;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.layout.*;
import javafx.stage.Stage;

/**
 * The class represents the second interface used to define the characteristics
 * of the rule being instantiated.
 *
 */
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
    private HBox triggerBox;
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
    @FXML
    private Label ruleDescription;
    //Custom items
    private HBox secondBoxAction = new HBox();
    private Label secondLabelAction = new Label("");
    private TextField secondTextFieldAction = new TextField();

    private HBox secondBoxTrigger = new HBox();
    private Label secondLabelTrigger = new Label("");
    private TextField secondTextFieldTrigger = new TextField();

    private HBox thirdBoxTrigger = new HBox();
    private Label thirdLabelTrigger = new Label("");
    private TextField thirdTextFieldTrigger = new TextField();

    private ActionContext actionContext = new ActionContext();
    private TriggerContext triggerContext = new TriggerContext();
    private CreatorTrigger trigger;
    private CreatorAction action;

    /**
     * As the name suggests, the method is used to initialize the interface's
     * elements and their properties.
     */
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

        secondBoxAction.getChildren().addAll(secondLabelAction, secondTextFieldAction);
        secondBoxAction.setMargin(secondLabelAction, new Insets(0, 10, 10, 0));
        secondBoxAction.setMargin(secondTextFieldAction, new Insets(0, 10, 10, 0));

        secondBoxTrigger.getChildren().addAll(secondLabelTrigger, secondTextFieldTrigger);
        secondBoxTrigger.setMargin(secondLabelTrigger, new Insets(0, 10, 10, 0));
        secondBoxTrigger.setMargin(secondTextFieldTrigger, new Insets(0, 10, 10, 0));

        thirdBoxTrigger.getChildren().addAll(thirdLabelTrigger, thirdTextFieldTrigger);
        thirdBoxTrigger.setMargin(thirdBoxTrigger, new Insets(0, 10, 10, 0));
        thirdBoxTrigger.setMargin(thirdBoxTrigger, new Insets(0, 10, 10, 0));
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
                state = new AudioActionUI(actionTextField, actionLabel, actionBox, ruleDescription);
                break;
            case MEX:
                state = new DialogBoxUI(actionLabel, ruleDescription);
                break;
            case COPY:
                state = new CopyFileActionUI(actionTextField, secondTextFieldAction, actionLabel, secondLabelAction, actionBox, secondBoxAction, vBox, ruleDescription);
                break;
            case MOVE:
                state = new MoveFileActionUI(actionTextField, secondTextFieldAction, actionLabel, secondLabelAction, actionBox, secondBoxAction, vBox, ruleDescription);
                break;
            case APPEND:
                state = new AppendToFileActionUI(secondTextFieldAction, actionLabel, secondLabelAction, secondBoxAction, vBox, ruleDescription);
                break;
            case EXECUTE:
                state = new ExecutorFileActionUI(actionTextField, secondTextFieldAction, actionLabel, secondLabelAction, actionBox, secondBoxAction, vBox, ruleDescription);
                break;
            case REMOVE:
                state = new RemoveFileActionUI(actionLabel, actionTextField, actionBox, ruleDescription);
        }
        actionContext.changeState(state);
        actionContext.setupUI();

    }

    /**
     * This method sets up the UI according to the type of Trigger selected.
     *
     * @param triggerType
     */
    private void setupTriggerUI(String triggerType) {
        TriggerMenuText enumType = TriggerMenuText.getByMenuText(triggerType);
        TriggerState state = null;

        switch (enumType) {
            case TIME:
                state = new TimeTriggerUI(triggerLabel, triggerTextField, ruleDescription);
                break;
            case DAYMONTH:
                state = new DayOfMonthTriggerUI(triggerLabel, triggerTextField, ruleDescription);
                break;
            case FILESIZE:
                state = new FileSizeTriggerUI(triggerLabel, secondLabelTrigger, triggerTextField, secondTextFieldTrigger, vBox, triggerBox, secondBoxTrigger, ruleDescription);
                break;
            case CURRENTDAY:
                state = new CurrentDayTriggerUI(triggerLabel, triggerTextField, ruleDescription);
                break;
            case EXIT:
                state = new ExitStatusTriggerUI(triggerLabel, secondLabelTrigger, thirdLabelTrigger, triggerTextField, secondTextFieldTrigger, thirdTextFieldTrigger, triggerBox, secondBoxTrigger, thirdBoxTrigger, vBox, ruleDescription);
                break;
            case DAYWEEK:
                state = new DayOfWeekTriggerUI(triggerLabel, triggerTextField, ruleDescription);
                break;
            case FINDFILE:
                state = new FoundFileTriggerUI(triggerLabel, secondLabelTrigger, triggerTextField, triggerBox, secondBoxTrigger, vBox, ruleDescription);
                break;
        }
        triggerContext.changeState(state);
        triggerContext.setupUI();
    }

    /**
     * The method receives the actionType and the triggerType composing the rule
     * being istantiated, and calls the appropriate UI setups methods.
     *
     * @param actionType The choosen type of action componing the rule.
     * @param triggerType The choosen type of trigger componing the rule.
     */
    public void initData(String actionType, String triggerType) {
        setupTriggerUI(triggerType);
        setupActionUI(actionType);

        Button b = (Button) rulesDialogPane.lookupButton(ButtonType.APPLY);
        b.setDefaultButton(true);
        b.disableProperty().bind(ruleTextField.textProperty().isEmpty().or(actionTextField.textProperty().isEmpty().or(triggerTextField.textProperty().isEmpty())));
        b.setOnAction(event -> createRule(actionType, triggerType, ruleTextField.getText()));

    }

    /**
     * The method is related to the edit action, and lets the user compare with
     * the rule being editing.
     *
     * @param actionType The choosen type of action componing the rule.
     * @param triggerType The choosen type of action componing the rule.
     * @param oldRule The rule being editing.
     */
    public void updateData(String actionType, String triggerType, Rule oldRule) {
        setupTriggerUI(triggerType);
        setupActionUI(actionType);

        ruleTextField.setText(oldRule.getNameRule());
        triggerTextField.setText(oldRule.getTrigger().getParam1());
        actionTextField.setText(oldRule.getAction().getParam1());
        secondTextFieldAction.setText(oldRule.getAction().getParam2());
        secondTextFieldTrigger.setText(oldRule.getTrigger().getParam2());
        thirdTextFieldTrigger.setText(oldRule.getTrigger().getParam3());

        Button b = (Button) rulesDialogPane.lookupButton(ButtonType.APPLY);
        b.setDefaultButton(true);
        b.disableProperty().bind(ruleTextField.textProperty().isEmpty().or(actionTextField.textProperty().isEmpty().or(triggerTextField.textProperty().isEmpty())));
        b.setOnAction(event -> editRule(oldRule, ruleTextField.getText(), actionType, triggerType));
        if (!oldRule.getPeriod().isZero()) {
            updateRepetionBox(oldRule);
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
                return new TimeTriggerCreator(LocalTime.parse(triggerTextField.getText(), DateTimeFormatter.ofPattern("HH:mm")));
            case DAYWEEK:
                return new DayOfWeekTriggerCreator(DayOfWeek.valueOf(triggerTextField.getText().replaceAll("\\s+", "").toUpperCase()));
            case FINDFILE:
                return new FoundFileTriggerCreator(triggerTextField.getText(), secondTextFieldTrigger.getText());
            case DAYMONTH:
                return new DayOfMonthTriggerCreator(parseInt(triggerTextField.getText()));
            case CURRENTDAY:
                return new CurrentDayTriggerCreator(LocalDate.parse(triggerTextField.getText()));
            case FILESIZE:
                return new FileSizeTriggerCreator(triggerTextField.getText(), parseLong(secondTextFieldTrigger.getText()));
            case EXIT:
                return new ExitStatusTriggerCreator(triggerTextField.getText(), secondTextFieldTrigger.getText().split(";"), parseInt(thirdTextFieldTrigger.getText()));
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
                return new AppendToFileActionCreator(actionTextField.getText(), secondTextFieldAction.getText());
            case COPY:
                return new CopyFileActionCreator(actionTextField.getText(), secondTextFieldAction.getText());
            case MOVE:
                return new MoveFileActionCreator(actionTextField.getText(), secondTextFieldAction.getText());
            case EXECUTE:
                return new ExecutorFileActionCreator(actionTextField.getText(), secondTextFieldAction.getText().split(";"));
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

    private void editRule(Rule oldRule, String ruleName, String actionType, String triggerType) {
        processRule(ruleName, actionType, triggerType, oldRule);
    }

    /**
     * The method is related to the edit action, and lets the user redefine when
     * the rule must repeat.
     *
     * @param oldRule The rule being replaced.
     */
    private void updateRepetionBox(Rule oldRule) {
        repetitionBox.setSelected(true);
        Pattern pattern = Pattern.compile("^PT((\\d+)H)?((\\d+)M)?((\\d+)(\\.\\d+)?S)?$"); //Pattern for the Duration string
        Matcher matcher = pattern.matcher(oldRule.getPeriod().toString());

        if (matcher.matches()) {
            int hours = Integer.parseInt(matcher.group(2) != null ? matcher.group(2) : "0");
            int minutes = Integer.parseInt(matcher.group(4) != null ? matcher.group(4) : "0");

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
