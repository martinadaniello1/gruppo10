package automatehub.controller;

import automatehub.model_view.FileExtensionFilter;
import automatehub.model_view.RemoveFileAction;
import automatehub.model_view.Rule;
import java.io.File;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;

public class RemoveFileActionUI extends ActionState {

    private Label actionLabel;
    private TextField actionTextField;
    private HBox hBox;

    public RemoveFileActionUI() {
    }

    public RemoveFileActionUI(Label actionLabel, TextField actionTextField, HBox hBox) {
        this.actionLabel = actionLabel;
        this.actionTextField = actionTextField;
        this.hBox = hBox;
    }

    @Override
    public void setupUI(ActionContext context) {
        actionLabel.setText("Select the file to remove:");
        actionTextField.setEditable(false);
        actionTextField.focusTraversableProperty().set(false);
        addFileChooser(hBox, FileExtensionFilter.ALL);
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
        RemoveFileAction removeFile = (RemoveFileAction) rule.getAction();
        alert.setTitle(rule.getNameRule() + " rule executed");
        alert.setHeaderText(null);
        alert.setContentText("Successful deletion of the " + removeFile.getFilePath()+ " file");
        // Show the dialog box 
        alert.show(); 
    }
}

