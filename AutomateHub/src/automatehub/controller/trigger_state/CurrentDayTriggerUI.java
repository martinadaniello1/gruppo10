package automatehub.controller.trigger_state;

import automatehub.controller.TriggerContext;
import automatehub.controller.trigger_state.TriggerState;
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

    @Override
    public void setupUI(TriggerContext context) {
        triggerLabel.setText("Select the day: ");
        label.setText("The rule will be verified when the current date is a specified date (e.g. 18/12/2023).");
        triggerTextField.setPromptText("e.g. yyyy-mm-gg");
        setUpTimeValidation();
    }

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
