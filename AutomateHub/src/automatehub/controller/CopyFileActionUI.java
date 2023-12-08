package automatehub.controller;

import automatehub.model_view.*;
import java.io.File;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;

/**
 * This class is the ActionState for the Action of copying a file to a
 * directory.
 */
public class CopyFileActionUI extends ActionState {

    private TextField actionTextField;
    private TextField secondTextField;
    private Label actionLabel;
    private Label secondLabel;
    private HBox hBox;
    private HBox hBox2;
    private VBox vBox;

    public CopyFileActionUI() {
    }

    public CopyFileActionUI(TextField actionTextField, TextField secondTextField, Label actionLabel, Label secondLabel, HBox hBox, HBox hBox2, VBox vBox) {
        this.actionTextField = actionTextField;
        this.secondTextField = secondTextField;
        this.actionLabel = actionLabel;
        this.secondLabel = secondLabel;
        this.hBox = hBox;
        this.hBox2 = hBox2;
        this.vBox = vBox;
    }

    @Override
    public void setupUI(ActionContext context) {
        this.actionLabel.setText("Choose the file you want to copy:");
        addFileChooser(hBox, FileExtensionFilter.ALL);
        actionTextField.setEditable(false);
        actionTextField.focusTraversableProperty().set(false);
        //Set up the new hbox
        vBox.getChildren().add(3, hBox2);
        secondLabel.setText("Choose the destination directory:");
        addFileChooser(hBox2, FileExtensionFilter.DIRECTORY);
        secondTextField.setEditable(false);
        secondTextField.focusTraversableProperty().set(false);
    }

    @Override
    public void exec(Rule rule) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        CopyFileAction copyAction = (CopyFileAction) rule.getAction();
        alert.setTitle(rule.getNameRule() + " rule executed");
        alert.setHeaderText(null);
        alert.setContentText("Successful copying of the " + copyAction.getStartingPath() + " file");
        //alert.getButtonTypes().setAll(ButtonType.OK);
        // Show the dialog box 
        alert.show(); //To change body of generated methods, choose Tools | Templates.
    }

}
