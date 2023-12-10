package automatehub.controller.trigger_state;

import automatehub.controller.TriggerContext;
import automatehub.controller.trigger_state.TriggerState;
import automatehub.model_view.FileExtensionFilter;
import java.util.function.UnaryOperator;
import javafx.geometry.Insets;
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
    private Label label;

    public ExitStatusTriggerUI() {
    }

    public ExitStatusTriggerUI(Label triggerLabel1, Label triggerLabel2, Label triggerLabel3, TextField triggerTextField1, TextField triggerTextField2, TextField triggerTextField3, HBox box1, HBox box2, HBox box3, VBox vbox, Label label) {
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
        this.label=label;
    }

    @Override
    public void setupUI(TriggerContext context) {
        label.setText("The rule will be verified when the exit status of the external program is equal to a specified value.");

        triggerLabel1.setText("Choose the program to monitor:");
        box1.setMargin(triggerLabel1, new Insets(0,0,0,0));
        addFileChooser(box1, FileExtensionFilter.PYTHON);
        triggerTextField1.setEditable(false);
        triggerTextField1.focusTraversableProperty().set(false);
        vbox.getChildren().add(3, box2);
        vbox.getChildren().add(4, box3);
        setUpValueValidation();
        //Set up the new box
        triggerLabel2.setText("Insert params: ");
        box2.setMargin(triggerLabel2, new Insets(0,65,0,0));
        triggerLabel3.setText("Insert an integer value: ");
        box3.setMargin(triggerLabel3, new Insets(0,10,0,0));
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
