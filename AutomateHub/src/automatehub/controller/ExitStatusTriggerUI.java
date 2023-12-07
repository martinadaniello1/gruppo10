package automatehub.controller;

import automatehub.model_view.FileExtensionFilter;
import java.util.function.UnaryOperator;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.converter.IntegerStringConverter;

/**
 * This class is the TriggerState for the Trigger ExitStatusTrigger.
 */
public class ExitStatusTriggerUI extends TriggerState{
    private Label triggerLabel1, triggerLabel2;
    private TextField triggerTextField1, triggerTextField2;
    private HBox box1,box2;
    private VBox vbox;

    public ExitStatusTriggerUI() {
    }

    public ExitStatusTriggerUI(Label triggerLabel1, Label triggerLabel2, TextField triggerTextField1, TextField triggerTextField2, HBox box1, HBox box2, VBox vbox) {
        this.triggerLabel1 = triggerLabel1;
        this.triggerLabel2 = triggerLabel2;
        this.triggerTextField1 = triggerTextField1;
        this.triggerTextField2 = triggerTextField2;
        this.box1 = box1;
        this.box2 = box2;
        this.vbox = vbox;
    }
    
    @Override
    public void setupUI(TriggerContext context) {
        triggerLabel1.setText("Choose the program you want to monitor: ");
        //addFileChooser(box1, FileExtensionFilter.ALL);
        triggerTextField1.setEditable(false);
        triggerTextField2.focusTraversableProperty().set(false);
        vbox.getChildren().add(3 , box2);
        setUpValueValidation();
    }

   private void setUpValueValidation() {
        triggerTextField2.setTextFormatter(new TextFormatter<>(new IntegerStringConverter(), 0,
                getIntegerFilter()));
    }

    private UnaryOperator<TextFormatter.Change> getIntegerFilter() {
        return change -> {
            String newText = change.getControlNewText();
            if (newText.matches("\\d*") && !newText.isEmpty()) {
                return change; // Accept the change
            } else {
                return null; // Reject the change (do not update the text)
            }
        };
    }
    
    
    
    
    
}
