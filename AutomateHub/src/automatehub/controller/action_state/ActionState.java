package automatehub.controller.action_state;

import automatehub.controller.ActionContext;
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
 * This class contains all the methods that the ActionState objects will
 * override, if necessary.
 */
public abstract class ActionState {
<<<<<<< HEAD

    /**
     * Sets up the user interface (UI). This method is meant to be implemented
     * by subclasses to configure the UI elements according to the specific
     * requirements of the given Action State.
     *
     * @param context The ActionContext containing information about the current
     * action.
=======
    
    /**
     * The method setups the UI based on the action choosen.
     * @param context 
>>>>>>> b4465dab6959225e163daaaab9facb0fd3fb1da9
     */
    public abstract void setupUI(ActionContext context);

    /**
     * Finds and returns the first TextField within the given HBox container.
     * Utility method.
     *
     * @param hbox The HBox container in which to search for a TextField.
     * @return The first TextField found in the HBox, or null if none is found.
     */
    public TextField findTextFieldInHBox(HBox hbox) {
        for (Node node : hbox.getChildren()) {
            if (node instanceof TextField) {
                return (TextField) node;
            }
        }
        return null;
    }

    /**
     * Adds a FileChooser or DirectoryChooser to the specified HBox, allowing
     * the user to choose a file or directory.
     *
     * @param box The HBox container to which the FileChooser or
     * DirectoryChooser button will be added.
     * @param fileFilter The file extension filter to apply, or
     * FileExtensionFilter.DIRECTORY for directories.
     */
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
<<<<<<< HEAD

    /**
     * This method is meant to be overridden by subclasses to provide custom
     * logic for executing the actions associated with the given Rule.
     *
     * @param rule The Rule to be executed.
=======
    
    /**
     * The method describes the behavior of the action based on the occurrence of the rule.
     * @param rule 
>>>>>>> b4465dab6959225e163daaaab9facb0fd3fb1da9
     */
    public void exec(Rule rule) {

    }

}
