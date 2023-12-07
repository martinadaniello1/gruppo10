/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package automatehub.controller;

import automatehub.controller.TriggerContext;
import automatehub.controller.TriggerState;
import automatehub.model_view.FileExtensionFilter;
import java.io.File;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;

/**
 *
 * @author Luca
 */
public class FoundFileTriggerUI extends TriggerState {

    private Label triggerLabel;
    private TextField triggerTextField;
    private HBox triggerHBox;
    private HBox dirBox;
    private Label dirLabel;
    private VBox vBox;
    private static TextField tf = new TextField();

    public FoundFileTriggerUI(Label triggerLabel, TextField triggerTextField, HBox triggerHBox, HBox dirBox, Label dirLabel, VBox vBox) {
        this.triggerLabel = triggerLabel;
        this.triggerTextField = triggerTextField;
        this.triggerHBox = triggerHBox;
        this.dirBox = dirBox;
        this.dirLabel = dirLabel;
        this.vBox = vBox;
    }

    @Override
    public void setupUI(TriggerContext context) {
        triggerLabel.setText("Insert the filename to find");
        triggerTextField.setPromptText("e.g. file.txt");
        setupFileValidation();
        vBox.getChildren().add(3, dirBox);
        dirLabel.setText("Choose where to search the file:");
        addDirChooser(dirBox, FileExtensionFilter.DIRECTORY);
    }

    private void setupFileValidation() {

        triggerTextField.focusedProperty().addListener((arg0, oldValue, newValue) -> {
            if (!newValue) {
                String newText = triggerTextField.getText().replaceAll("\\\\+", "");
                triggerTextField.setText(newText);

            }

        });
    }

    public void addDirChooser(HBox box, FileExtensionFilter filter) {
        Button fileChooserButton = new Button("...");
        box.getChildren().add(fileChooserButton);
        box.setMargin(fileChooserButton, new Insets(0, 10, 15, 0));
        

        if (filter.equals(FileExtensionFilter.DIRECTORY)) {
            box.getChildren().add(tf);

            fileChooserButton.setOnAction(event -> {
                DirectoryChooser directoryChooser = new DirectoryChooser();
                directoryChooser.setTitle("Choose the directory");

                File selectedFile = directoryChooser.showDialog(box.getScene().getWindow());
                if (selectedFile != null) {
                    tf.setText(selectedFile.getAbsolutePath());
                   
                }
            });

        }
        

    }

    public static String getTf() {
        return tf.getText();
    }
    
    

}
