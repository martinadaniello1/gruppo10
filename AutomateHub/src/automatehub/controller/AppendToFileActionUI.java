/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package automatehub.controller;

import automatehub.model_view.AppendToFileAction;
import automatehub.model_view.FileExtensionFilter;
import automatehub.model_view.Rule;
import java.io.File;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;

/**
 *
 * @author mapic
 */
public class AppendToFileActionUI extends ActionState {
    private TextField secondTextField;
    private Label actionLabel;
    private Label secondLabel;
    private HBox hBox2;
    private VBox vBox;

    public AppendToFileActionUI() {
    }
    
    public AppendToFileActionUI(TextField secondTextField, Label actionLabel, Label secondLabel, HBox hBox2, VBox vBox) {
        this.secondTextField = secondTextField;
        this.actionLabel = actionLabel;
        this.secondLabel = secondLabel;
        this.hBox2 = hBox2;
        this.vBox = vBox;
    }    
    
    @Override
    public void setupUI(ActionContext context) {
        this.actionLabel.setText("Write the text to append:");
        //Set up the new hbox
        vBox.getChildren().add(3, hBox2);
        secondLabel.setText("Choose the text file:");
        addFileChooser(hBox2, FileExtensionFilter.TEXT);
        secondTextField.setEditable(false);
        secondTextField.focusTraversableProperty().set(false);  
    }
    
     @Override
    public void addFileChooser(HBox box, FileExtensionFilter fileFilter) {
        Button fileChooserButton = new Button("...");
        box.getChildren().add(fileChooserButton);
        TextField tf = findTextFieldInHBox(box);

        if (fileFilter != FileExtensionFilter.DIRECTORY) {
            fileChooserButton.setOnAction(event -> {
                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Choose the file");

                if (fileFilter != FileExtensionFilter.ALL) {
                    FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
                            fileFilter.getDescription(),
                            fileFilter.getExtension()
                    );
                    fileChooser.getExtensionFilters().add(extFilter);
                }
                File selectedFile = fileChooser.showOpenDialog(box.getScene().getWindow());
                if (selectedFile != null) {
                    tf.setText(selectedFile.getAbsolutePath());
                }
            });
        } else {
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

    @Override
    public void exec(Rule rule) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        AppendToFileAction appendAction = (AppendToFileAction) rule.getAction();
        alert.setTitle( rule.getNameRule() + " rule executed");
        alert.setHeaderText(null);
        alert.setContentText("String " + appendAction.getStringToAppend() + " successfully added to "+ appendAction.getFilePath() + " file");
        //alert.getButtonTypes().setAll(ButtonType.OK);
        // Show the dialog box 
        alert.show();
    }
    
    
}
