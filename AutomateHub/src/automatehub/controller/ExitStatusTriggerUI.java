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
public class ExitStatusTriggerUI extends TriggerState {

    private Label triggerLabel1, triggerLabel2, triggerLabel3;
    private TextField triggerTextField1, triggerTextField2, triggerTextField3;
    private HBox box1, box2, box3;
    private VBox vbox;

    public ExitStatusTriggerUI() {
    }

    public ExitStatusTriggerUI(Label triggerLabel1, Label triggerLabel2, Label triggerLabel3, TextField triggerTextField1, TextField triggerTextField2, TextField triggerTextField3, HBox box1, HBox box2, HBox box3, VBox vbox) {
        this.triggerLabel1 = triggerLabel1;
        this.triggerLabel2 = triggerLabel2;
        this.triggerLabel3 = triggerLabel3;
        this.triggerTextField1 = triggerTextField1;
        this.triggerTextField2 = triggerTextField2;
        this.triggerTextField3 = triggerTextField3;
        this.box1 = box1;
        this.box2 = box2;
        this.box3 = box3;
        this.vbox = vbox;
    }

    @Override
    public void setupUI(TriggerContext context) {
        triggerLabel1.setText("Choose the program you want to monitor: ");
        addFileChooser(box1, FileExtensionFilter.PYTHON);
        triggerTextField1.setEditable(false);
        triggerTextField1.focusTraversableProperty().set(false);
        vbox.getChildren().add(2, box2);
        vbox.getChildren().add(3, box3);
        setUpValueValidation();
        //Set up the new box
        triggerLabel2.setText("Insert params: ");
        triggerLabel3.setText("Insert an integer value: ");
        triggerTextField2.setPromptText("arg1;arg2; ...; argn; ");
    }
    //Check if the value is an integer 
    private void setUpValueValidation() {
        triggerTextField3.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                triggerTextField3.setText(oldValue);
            }
        });
    }
}
