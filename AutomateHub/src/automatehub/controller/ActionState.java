package automatehub.controller;

import automatehub.model_view.FileExtensionFilter;
import automatehub.model_view.Rule;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

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

    }

    public void exec(Rule rule) {

    }

}
