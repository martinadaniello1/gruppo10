package automatehub.controller.trigger_state;

import automatehub.controller.TriggerContext;
import automatehub.model_view.FileExtensionFilter;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.*;

/**
 * This class is the TriggerState for the Trigger FileSizeTrigger.
 */
public class FileSizeTriggerUI extends TriggerState {

    private Label triggerLabel1;
    private Label triggerLabel2;
    private TextField triggerTextField1;
    private TextField triggerTextField2;
    private VBox vBox;
    private HBox hBox1, hBox2;
    private Label label;

    public FileSizeTriggerUI(Label triggerLabel1, Label triggerLabel2, TextField triggerTextField1, TextField triggerTextField2, VBox vBox, HBox hBox1, HBox hBox2, Label label) {
        this.triggerLabel1 = triggerLabel1;
        this.triggerLabel2 = triggerLabel2;
        this.triggerTextField1 = triggerTextField1;
        this.triggerTextField2 = triggerTextField2;
        this.vBox = vBox;
        this.hBox1 = hBox1;
        this.hBox2 = hBox2;
        this.label = label;
    }

    /**
     * Sets up the UI elements for the FileSizeTrigger based on the provided
     * TriggerContext.
     *
     * @param context The TriggerContext containing information about the current
     * trigger.
     */
    @Override
    public void setupUI(TriggerContext context) {
        label.setText("The rule will be verified when the size of a specified file is larger than a specified value in bytes.");

        triggerLabel1.setText("Choose the file: ");
        addFileChooser(hBox1, FileExtensionFilter.ALL);
        triggerTextField1.setEditable(false);
        triggerTextField1.focusTraversableProperty().set(false);
        vBox.getChildren().add(3, hBox2);
        setUpValueValidation();
        //Set up the new box
        hBox2.setMargin(triggerLabel2, new Insets(0, 10, 0, 0));
        triggerLabel2.setText("Insert the size in bytes to compare: ");
    }

     /**
     * Checks if the user's input is valid.
     */
    private void setUpValueValidation() {
        triggerTextField2.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                triggerTextField2.setText(oldValue);
            }
        });
    }
}
