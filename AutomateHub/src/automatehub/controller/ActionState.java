package automatehub.controller;

import automatehub.model_view.CreatorAction;
import automatehub.model_view.FileExtensionFilter;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

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

    public abstract CreatorAction getCreator(ActionContext aThis);
}
