package automatehub.controller.trigger_state;

import automatehub.controller.TriggerContext;
import automatehub.controller.trigger_state.TriggerState;
import automatehub.model_view.FileExtensionFilter;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class FileSizeTriggerUI extends TriggerState {
    private Label triggerLabel1;
    private Label triggerLabel2;
    private TextField triggerTextField1;
    private TextField triggerTextField2;
    private VBox vBox;
    private HBox hBox1, hBox2;

    public FileSizeTriggerUI(Label triggerLabel1, Label triggerLabel2, TextField triggerTextField1, TextField triggerTextField2, VBox vBox, HBox hBox1, HBox hBox2) {
        this.triggerLabel1 = triggerLabel1;
        this.triggerLabel2 = triggerLabel2;
        this.triggerTextField1 = triggerTextField1;
        this.triggerTextField2 = triggerTextField2;
        this.vBox = vBox;
        this.hBox1 = hBox1;
        this.hBox2 = hBox2;
    }

    @Override
    public void setupUI(TriggerContext context) {
        triggerLabel1.setText("Choose the file: ");
        addFileChooser(hBox1, FileExtensionFilter.ALL);
        triggerTextField1.setEditable(false);
        triggerTextField1.focusTraversableProperty().set(false);
        vBox.getChildren().add(2 , hBox2);
        setUpValueValidation();
        //Set up the new box
        hBox2.setMargin(triggerLabel2, new Insets(0,10,0,0));
        triggerLabel2.setText("Insert the size in bytes to compare: ");
    }
    
    private void setUpValueValidation(){
        triggerTextField2.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                triggerTextField2.setText(oldValue);
            }
        });
    }
 }
