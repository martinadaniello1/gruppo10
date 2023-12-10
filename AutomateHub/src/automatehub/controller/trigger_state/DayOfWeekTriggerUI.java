package automatehub.controller.trigger_state;

import automatehub.controller.TriggerContext;
import java.util.Arrays;
import java.util.List;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * This class is the TriggerState for the Trigger DayOfWeekTrigger.
 */
public class DayOfWeekTriggerUI extends TriggerState {

    private Label triggerLabel;
    private TextField triggerTextField;
    private Label label;

    public DayOfWeekTriggerUI(Label triggerLabel, TextField triggerTextField, Label label) {
        this.triggerLabel = triggerLabel;
        this.triggerTextField = triggerTextField;
        this.label = label;
    }

    /**
     * Sets up the UI elements for the DayOfWeekTrigger based on the provided
     * TriggerContext.
     *
     * @param context The TriggerContext containing information about the current
     * trigger.
     */
    @Override
    public void setupUI(TriggerContext context) {
        label.setText("The rule will be verified when the current day-of-week is a specified day (e.g. \"Monday\").");

        triggerLabel.setText("Insert the day: ");
        triggerTextField.setPromptText("e.g. Saturday");
        setupDayValidation();
    }

    /**
     * Checks if the user's input is valid.
     */
    private void setupDayValidation() {

        List<String> daysOfWeek = Arrays.asList("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday");
        triggerTextField.focusedProperty().addListener((arg0, oldValue, newValue) -> {
            if (!newValue) {
                if (!(daysOfWeek.contains(triggerTextField.getText()) || daysOfWeek.contains(triggerTextField.getText().trim()))) {
                    triggerTextField.setText("");
                }
            }
        });

    }

}
