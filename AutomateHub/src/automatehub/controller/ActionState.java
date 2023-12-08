package automatehub.controller;

import automatehub.model_view.FileExtensionFilter;
import automatehub.model_view.Rule;
import java.io.File;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;

/**
 * This class contains all the methods that the ActionState objects will override, if necessary.
 */
public abstract class ActionState {

    public abstract void setupUI(ActionContext context);

    public TextField findTextFieldInHBox(HBox hbox) {
        for (Node node : hbox.getChildren()) {
            if (node instanceof TextField) {
                return (TextField) node;
            }
        }
        return null;
    }

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

    public void exec(Rule rule) {

    }

}
