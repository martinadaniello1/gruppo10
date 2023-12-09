package automatehub.controller;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * This class is the TriggerState for the Trigger DayOfMonthTrigger.
 */
public class DayOfMonthTriggerUI extends TriggerState {
    
    private Label triggerLabel;
    private TextField triggerTextField;

    public DayOfMonthTriggerUI(Label triggerLabel, TextField triggerTextField) {
        this.triggerLabel = triggerLabel;
        this.triggerTextField = triggerTextField;
    }
    
    @Override
    public void setupUI(TriggerContext context) {
        triggerLabel.setText("Select the day of the month: ");
        triggerTextField.setPromptText("e.g. 4 (every month on the 4th)");
        setupDayValidation();
    }
    
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
