package automatehub.controller.action_state;

import automatehub.controller.ActionContext;
import automatehub.model_view.FileExtensionFilter;
import automatehub.model_view.action.RemoveFileAction;
import automatehub.model_view.Rule;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;

public class RemoveFileActionUI extends ActionState {

    private Label actionLabel;
    private TextField actionTextField;
    private HBox hBox;
    private Label label;

    public RemoveFileActionUI() {
    }

    public RemoveFileActionUI(Label actionLabel, TextField actionTextField, HBox hBox, Label label) {
        this.actionLabel = actionLabel;
        this.actionTextField = actionTextField;
        this.hBox = hBox;
        this.label = label;
    }

    /**
     * Sets up the UI elements for the RemoveFileAction based on the provided
     * ActionContext.
     *
     * @param context The ActionContext containing information about the current
     * action.
     */
    @Override
    public void setupUI(ActionContext context) {
        label.setText(label.getText() + "\n" + "When the rule is verified, the action will be removing a specified file from a specified directory.");

        actionLabel.setText("Select the file to remove: ");
        actionTextField.setEditable(false);
        actionTextField.focusTraversableProperty().set(false);
        addFileChooser(hBox, FileExtensionFilter.ALL);
    }

    /**
     * Displays an information alert about the successful execution of the
     * RemoveFileAction associated with the rule.
     *
     * @param rule The Rule that was executed.
     */
    @Override
    public void exec(Rule rule) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        RemoveFileAction removeFile = (RemoveFileAction) rule.getAction();
        alert.setTitle(rule.getNameRule() + " rule executed");
        alert.setHeaderText(null);
        alert.setContentText("Successful deletion of the " + removeFile.getFilePath() + " file");
        // Show the dialog box 
        alert.show();
    }
}
