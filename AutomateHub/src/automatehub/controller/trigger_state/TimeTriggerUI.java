package automatehub.controller.trigger_state;

import automatehub.controller.TriggerContext;
import automatehub.controller.trigger_state.TriggerState;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * This class is the TriggerState for the Trigger TimeTrigger.
 */
public class TimeTriggerUI extends TriggerState {

    private Label triggerLabel;
    private TextField triggerTextField;
    private Label label;

    public TimeTriggerUI(Label triggerLabel, TextField triggerTextField, Label label) {
        this.triggerLabel = triggerLabel;
        this.triggerTextField = triggerTextField;
        this.label=label;
    }

    @Override
    public void setupUI(TriggerContext context) {
        label.setText("The rule will be verified when the current time is a specified time (e.g. \"11.37\").");

        triggerLabel.setText("Select the time: ");
        triggerTextField.setPromptText("e.g. 11:37");
        setupTimeValidation();
    }

    private void setupTimeValidation() {
        triggerTextField.focusedProperty().addListener((arg0, oldValue, newValue) -> {
            if (!newValue) {
                if (!triggerTextField.getText().matches("^(0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]$")) {
                    triggerTextField.setText("");
                }
            }
        });

    }

}
