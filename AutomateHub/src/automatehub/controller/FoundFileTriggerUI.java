/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package automatehub.controller;

import automatehub.controller.TriggerContext;
import automatehub.controller.TriggerState;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 *
 * @author Luca
 */
public class FoundFileTriggerUI extends TriggerState {

    private Label triggerLabel;
    private TextField triggerTextField;

    public FoundFileTriggerUI(Label triggerLabel, TextField triggerTextField) {
        this.triggerLabel = triggerLabel;
        this.triggerTextField = triggerTextField;
    }

    @Override
    public void setupUI(TriggerContext context) {
        triggerLabel.setText("Insert the filename to find");
        triggerTextField.setPromptText("e.g. file.txt");
        setupFileValidation();
    }

    private void setupFileValidation() {

        triggerTextField.focusedProperty().addListener((arg0, oldValue, newValue) -> {
            if (!newValue) {
                String newText = triggerTextField.getText().replaceAll("\\\\+", "");
                triggerTextField.setText(newText);

            }

        });
    }

}
