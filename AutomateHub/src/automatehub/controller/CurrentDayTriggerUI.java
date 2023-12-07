package automatehub.controller;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * This class is the TriggerState for the Trigger CurrentDayTrigger.
 */
public class CurrentDayTriggerUI extends TriggerState {

    private Label triggerLabel;
    private TextField triggerTextField;

    public CurrentDayTriggerUI(Label triggerLabel, TextField triggerTextField) {
        this.triggerLabel = triggerLabel;
        this.triggerTextField = triggerTextField;
    }

    public CurrentDayTriggerUI() {
    }

    @Override
    public void setupUI(TriggerContext context) {
        triggerLabel.setText("Select the day: ");
        triggerTextField.setPromptText("e.g. yyyy-mm-gg");
        setUpTimeValidaation();
    }

    private void setUpTimeValidaation() {
        triggerTextField.focusedProperty().addListener((arg0, oldValue, newValue) -> {
            if (!newValue) {
                if (!triggerTextField.getText().matches("^\\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[01])$")) {
                    triggerTextField.setText("");
                }
            }
        });
    }

}
