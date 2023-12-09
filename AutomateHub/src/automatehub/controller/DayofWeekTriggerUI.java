package automatehub.controller;

import automatehub.controller.TriggerContext;
import automatehub.controller.TriggerState;
import java.util.Arrays;
import java.util.List;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class DayOfWeekTriggerUI extends TriggerState {

    private Label triggerLabel;
    private TextField triggerTextField;

    public DayOfWeekTriggerUI(Label triggerLabel, TextField triggerTextField) {
        this.triggerLabel = triggerLabel;
        this.triggerTextField = triggerTextField;
    }

    @Override
    public void setupUI(TriggerContext context) {
        triggerLabel.setText("Insert the day: ");
        triggerTextField.setPromptText("e.g. Saturday");
        setupDayValidation();
    }

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
