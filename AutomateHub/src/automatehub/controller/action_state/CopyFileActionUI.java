package automatehub.controller.action_state;

import automatehub.controller.ActionContext;
import automatehub.model_view.action.CopyFileAction;
import automatehub.model_view.*;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.*;

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
    private Label label;

    public CopyFileActionUI() {
    }

    public CopyFileActionUI(TextField actionTextField, TextField secondTextField, Label actionLabel, Label secondLabel, HBox hBox, HBox hBox2, VBox vBox, Label label) {
        this.actionTextField = actionTextField;
        this.secondTextField = secondTextField;
        this.actionLabel = actionLabel;
        this.secondLabel = secondLabel;
        this.hBox = hBox;
        this.hBox2 = hBox2;
        this.vBox = vBox;
        this.label = label;
    }

    /**
     * Sets up the UI elements for the CopyFileAction based on the provided
     * ActionContext.
     *
     * @param context The ActionContext containing information about the current
     * action.
     */
    @Override
    public void setupUI(ActionContext context) {
        label.setText(label.getText() + "\n" + "When the rule is verified, the action will be copying a specified file from a specified source directory to a specified destination directory.");

        this.actionLabel.setText("Choose the file to copy:");
        hBox.setMargin(actionLabel, new Insets(0, 63, 0, 0));
        addFileChooser(hBox, FileExtensionFilter.ALL);
        actionTextField.setEditable(false);
        actionTextField.focusTraversableProperty().set(false);
        //Set up the new hbox
        vBox.getChildren().add(6, hBox2);
        secondLabel.setText("Choose the destination directory:");
        addFileChooser(hBox2, FileExtensionFilter.DIRECTORY);
        secondTextField.setEditable(false);
        secondTextField.focusTraversableProperty().set(false);
    }

    /**
     * Displays an information alert about the successful execution of the
     * CopyFileAction associated with the rule.
     *
     * @param rule The Rule that was executed.
     */
    @Override
    public void exec(Rule rule) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        CopyFileAction copyAction = (CopyFileAction) rule.getAction();
        alert.setTitle(rule.getNameRule() + " rule executed");
        alert.setHeaderText(null);
        alert.setContentText("Successful copying of the " + copyAction.getStartingPath() + " file");
        // Show the dialog box 
        alert.show();
    }

}
