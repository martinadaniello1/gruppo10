package automatehub.controller.trigger_state;

import automatehub.controller.TriggerContext;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * This class is the TriggerState for the Trigger DayOfMonthTrigger.
 */
public class DayOfMonthTriggerUI extends TriggerState {
    
    private Label triggerLabel;
    private TextField triggerTextField;
    private Label label;

    public DayOfMonthTriggerUI(Label triggerLabel, TextField triggerTextField, Label label) {
        this.triggerLabel = triggerLabel;
        this.triggerTextField = triggerTextField;
        this.label=label;
    }
    
    /**
     * Sets up the UI elements for the DayOfMonthTrigger based on the provided
     * TriggerContext.
     *
     * @param context The TriggerContext containing information about the current
     * trigger.
     */
    @Override
    public void setupUI(TriggerContext context) {
        label.setText("The rule will be verified when the current day-of-month is a specified day (e.g. 22).");
        triggerLabel.setText("Select the day of the month: ");
        triggerTextField.setPromptText("e.g. 4 (every month on the 4th)");
        setupDayValidation();
    }
    
    /**
     * Checks if the user's input is valid.
     */
    private void setupDayValidation() {
        triggerTextField.focusedProperty().addListener((arg0, oldValue, newValue) -> {
            if (!newValue) {
                if (!triggerTextField.getText().matches("^(0?[1-9]|[12][0-9]|3[01])$")) {
                    triggerTextField.setText("");
                }
            }
        });
    }
    
}
