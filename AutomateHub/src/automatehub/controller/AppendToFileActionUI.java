package automatehub.controller;

import automatehub.model_view.*;
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
 * This class is the ActionState for the Action of appending a string to a file.
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
