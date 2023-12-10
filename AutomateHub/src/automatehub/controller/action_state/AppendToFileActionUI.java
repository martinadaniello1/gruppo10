package automatehub.controller.action_state;

import automatehub.controller.ActionContext;
import automatehub.model_view.action.AppendToFileAction;
import automatehub.model_view.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;

/**
 * This class represents the ActionState (UI) for the Action of appending a
 * string to a file.
 */
public class AppendToFileActionUI extends ActionState {

    private TextField secondTextField;
    private Label actionLabel;
    private Label secondLabel;
    private HBox hBox2;
    private VBox vBox;
    private Label label;

    public AppendToFileActionUI() {
    }

    public AppendToFileActionUI(TextField secondTextField, Label actionLabel, Label secondLabel, HBox hBox2, VBox vBox, Label label) {
        this.secondTextField = secondTextField;
        this.actionLabel = actionLabel;
        this.secondLabel = secondLabel;
        this.hBox2 = hBox2;
        this.vBox = vBox;
        this.label=label;
    }

    /**
     * Sets up the UI elements for the AppendToFileAction based on the provided
     * ActionContext.
     *
     * @param context The ActionContext containing information about the current
     * action.
     */
    @Override
    public void setupUI(ActionContext context) {
        this.actionLabel.setText("Write the text to append:");
        label.setText(label.getText()+"\n"+"When the rule is verified, the action will be writing a specified string at the end of a specified text file.");
        //Set up the new hbox
        vBox.getChildren().add(5, hBox2);
        secondLabel.setText("Choose the text file:");
        addFileChooser(hBox2, FileExtensionFilter.TEXT);
        secondTextField.setEditable(false);
        secondTextField.focusTraversableProperty().set(false);
    }

    /**
     * Displays an information alert about the successful execution of the
     * AppendToFileAction associated with the rule.
     *
     * @param rule The Rule that was executed.
     */
    @Override
    public void exec(Rule rule) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        AppendToFileAction appendAction = (AppendToFileAction) rule.getAction();
        alert.setTitle(rule.getNameRule() + " rule executed");
        alert.setHeaderText(null);
        alert.setContentText("String " + appendAction.getStringToAppend() + " successfully added to " + appendAction.getFilePath() + " file");
        // Show the dialog box 
        alert.show();
    }

}
