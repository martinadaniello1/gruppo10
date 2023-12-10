package automatehub.controller.trigger_state;

import automatehub.controller.TriggerContext;
import automatehub.model_view.FileExtensionFilter;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * This class is the TriggerState for the Trigger FoundFileTrigger.
 */
public class FoundFileTriggerUI extends TriggerState {

    private Label triggerLabel, secondTriggerLabel;
    private TextField triggerTextField;
    private HBox triggerBox, secondTriggerBox;
    private VBox vBox;
    private Label label;

    public FoundFileTriggerUI() {
    }

    public FoundFileTriggerUI(Label triggerLabel, Label secondTriggerLabel, TextField triggerTextField, HBox triggerBox, HBox secondTriggerBox, VBox vBox, Label label) {
        this.triggerLabel = triggerLabel;
        this.secondTriggerLabel = secondTriggerLabel;
        this.triggerTextField = triggerTextField;
        this.triggerBox = triggerBox;
        this.secondTriggerBox = secondTriggerBox;
        this.vBox = vBox;
        this.label = label;
    }

    /**
     * Sets up the UI elements for the FoundFileTrigger based on the provided
     * TriggerContext.
     *
     * @param context The TriggerContext containing information about the
     * current trigger.
     */
    @Override
    public void setupUI(TriggerContext context) {
        label.setText("The rule will be verified when the specified file exists in a specified directory.");
        triggerLabel.setText("Insert the filename to find: ");
        triggerBox.setMargin(triggerLabel, new Insets(0, 35, 0, 0));
        triggerTextField.setPromptText("e.g. file.txt");
        setupFileValidation();
        vBox.getChildren().add(3, secondTriggerBox);
        secondTriggerLabel.setText("Choose where to search the file: ");
        secondTriggerBox.setMargin(secondTriggerLabel, new Insets(0, 10, 0, 0));
        addFileChooser(secondTriggerBox, FileExtensionFilter.DIRECTORY);
    }

    /**
     * Checks if the user's input is valid.
     */
    private void setupFileValidation() {
        triggerTextField.focusedProperty().addListener((arg0, oldValue, newValue) -> {
            if (!newValue) {
                String newText = triggerTextField.getText().replaceAll("\\\\+", "");
                triggerTextField.setText(newText);
            }
        });
    }

}
