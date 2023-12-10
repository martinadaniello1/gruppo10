package automatehub.controller.trigger_state;

import automatehub.controller.TriggerContext;
import automatehub.controller.trigger_state.TriggerState;
import automatehub.model_view.FileExtensionFilter;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class FoundFileTriggerUI extends TriggerState {

    private Label triggerLabel, secondTriggerLabel;
    private TextField triggerTextField, secondTextFieldTrigger;
    private HBox triggerBox, secondTriggerBox;
    private VBox vBox;
    private Label label;

    public FoundFileTriggerUI() {
    }

    public FoundFileTriggerUI(Label triggerLabel, Label secondTriggerLabel, TextField triggerTextField, TextField secondTextFieldTrigger, HBox triggerBox, HBox secondTriggerBox, VBox vBox, Label label) {
        this.triggerLabel = triggerLabel;
        this.secondTriggerLabel = secondTriggerLabel;
        this.triggerTextField = triggerTextField;
        this.secondTextFieldTrigger = secondTextFieldTrigger;
        this.triggerBox = triggerBox;
        this.secondTriggerBox = secondTriggerBox;
        this.vBox = vBox;
        this.label = label;
    }

    @Override
    public void setupUI(TriggerContext context) {
        label.setText("The rule will be verified when  file with a specified name exists in a specified directory.");
        triggerLabel.setText("Insert the filename to find: ");
        triggerBox.setMargin(triggerLabel, new Insets(0, 35, 0, 0));
        triggerTextField.setPromptText("e.g. file.txt");
        setupFileValidation();
        vBox.getChildren().add(3, secondTriggerBox);
        secondTriggerLabel.setText("Choose where to search the file: ");
        secondTriggerBox.setMargin(secondTriggerLabel, new Insets(0, 10, 0, 0));
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
