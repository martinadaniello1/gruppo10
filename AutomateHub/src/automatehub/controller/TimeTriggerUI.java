package automatehub.controller;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * This class is the TriggerState for the Trigger TimeTrigger.
 */
public class TimeTriggerUI extends TriggerState {

    private Label triggerLabel;
    private TextField triggerTextField;

    public TimeTriggerUI(Label triggerLabel, TextField triggerTextField) {
        this.triggerLabel = triggerLabel;
        this.triggerTextField = triggerTextField;
    }

    @Override
    public void setupUI(TriggerContext context) {
        triggerLabel.setText("Select the time: ");
        triggerTextField.setPromptText("e.g. 17:30");
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
