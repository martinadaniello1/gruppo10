/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package automatehub.controller;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 *
 * @author mapic
 */
public class TimeTriggerUI extends TriggerState{

    private Label triggerLabel;
    private TextField triggerTextField;

    public TimeTriggerUI(Label triggerLabel, TextField triggerTextField) {
        this.triggerLabel = triggerLabel;
        this.triggerTextField = triggerTextField;
    }
    
    @Override
    public void setupUI(TriggerContext context) {
        triggerLabel.setText("Select the time");
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
