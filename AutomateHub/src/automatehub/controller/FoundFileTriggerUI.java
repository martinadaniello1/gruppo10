package automatehub.controller;

import automatehub.model_view.FileExtensionFilter;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class FoundFileTriggerUI extends TriggerState {

    private Label triggerLabel, secondTriggerLabel;
    private TextField triggerTextField, secondTextFieldTrigger;
    private HBox triggerBox, secondTriggerBox;
    private VBox vBox;

    public FoundFileTriggerUI() {
    }

    public FoundFileTriggerUI(Label triggerLabel, Label secondTriggerLabel, TextField triggerTextField, TextField secondTextFieldTrigger, HBox triggerBox, HBox secondTriggerBox, VBox vBox) {
        this.triggerLabel = triggerLabel;
        this.secondTriggerLabel = secondTriggerLabel;
        this.triggerTextField = triggerTextField;
        this.secondTextFieldTrigger = secondTextFieldTrigger;
        this.triggerBox = triggerBox;
        this.secondTriggerBox = secondTriggerBox;
        this.vBox = vBox;
    }

    @Override
    public void setupUI(TriggerContext context) {
        triggerLabel.setText("Insert the filename to find: ");
        triggerTextField.setPromptText("e.g. file.txt");
        setupFileValidation();
        vBox.getChildren().add(2, secondTriggerBox);
        secondTriggerLabel.setText("Choose where to search the file: ");
        addFileChooser(secondTriggerBox, FileExtensionFilter.DIRECTORY);
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
