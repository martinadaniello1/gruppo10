package automatehub.controller.trigger_state;

import automatehub.controller.TriggerContext;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * This class is the TriggerState for the Trigger CurrentDayTrigger.
 */
public class CurrentDayTriggerUI extends TriggerState {

    private Label triggerLabel;
    private TextField triggerTextField;
    private Label label;

    public CurrentDayTriggerUI(Label triggerLabel, TextField triggerTextField, Label label) {
        this.triggerLabel = triggerLabel;
        this.triggerTextField = triggerTextField;
        this.label = label;
    }

    public CurrentDayTriggerUI() {
    }

    /**
     * Sets up the UI elements for the CurrentDayTrigger based on the provided
     * TriggerContext.
     *
     * @param context The TriggerContext containing information about the current
     * trigger.
     */
    @Override
    public void setupUI(TriggerContext context) {
        triggerLabel.setText("Select the day: ");
        label.setText("The rule will be verified when the current date is a specified date (e.g. 2023-12-10).");
        triggerTextField.setPromptText("e.g. yyyy-mm-gg");
        setUpTimeValidation();
    }

    /**
     * Checks if the user's input is valid.
     */
    private void setUpTimeValidation() {
        triggerTextField.focusedProperty().addListener((arg0, oldValue, newValue) -> {
            if (!newValue) {
                if (!triggerTextField.getText().matches("^\\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[01])$")) {
                    triggerTextField.setText("");
                }
            }
        });
    }

}
