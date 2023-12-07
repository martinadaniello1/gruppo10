/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package automatehub.controller;

import automatehub.controller.TriggerContext;
import automatehub.controller.TriggerState;
import java.util.Arrays;
import java.util.List;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 *
 * @author Luca
 */
public class DayofWeekTriggerUI extends TriggerState {

    private Label triggerLabel;
    private TextField triggerTextField;

    public DayofWeekTriggerUI(Label triggerLabel, TextField triggerTextField) {
        this.triggerLabel = triggerLabel;
        this.triggerTextField = triggerTextField;
    }

    @Override
    public void setupUI(TriggerContext context) {
        triggerLabel.setText("Insert the day");
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
