package automatehub.controller.action_state;

import automatehub.controller.ActionContext;
import automatehub.model_view.action.ExecutorFileAction;
import automatehub.model_view.*;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.*;

/**
 * This class represents the ActionState (UI) for the Action of executing an
 * external program.
 *
 */
public class ExecutorFileActionUI extends ActionState {

    private TextField actionTextField;
    private TextField secondTextField;
    private Label actionLabel;
    private Label secondLabel;
    private HBox hBox;
    private HBox hBox2;
    private VBox vBox;
    private Label label;

    public ExecutorFileActionUI() {
    }

    public ExecutorFileActionUI(TextField actionTextField, TextField secondTextField, Label actionLabel, Label secondLabel, HBox hBox, HBox hBox2, VBox vBox, Label label) {
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
     * Sets up the UI elements for the ExecutorFileAction based on the provided
     * ActionContext.
     *
     * @param context The ActionContext containing information about the current
     * action.
     */
    @Override
    public void setupUI(ActionContext context) {
        label.setText(label.getText() + "\n" + "When the rule is verified, the action will be executing a specified external program, with specified command-line arguments.");
        this.actionLabel.setText("Choose a programm to run: ");
        addFileChooser(hBox, FileExtensionFilter.PYTHON);
        actionTextField.setEditable(false);
        actionTextField.focusTraversableProperty().set(false);
        vBox.getChildren().add(6, hBox2);
        hBox2.setMargin(secondLabel, new Insets(0, 110, 0, 0));
        this.secondLabel.setText("Insert arguments: ");
        secondTextField.setPromptText("Arg1 ; Arg2 ; ... ; ArgN");
        secondTextField.focusTraversableProperty().set(false);
    }

    /**
     * Displays an information alert about the successful execution of the
     * ExecutorFileAction associated with the rule.
     *
     * @param rule The Rule that was executed.
     */
    @Override
    public void exec(Rule rule) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        ExecutorFileAction executedAction = (ExecutorFileAction) rule.getAction();
        alert.setTitle(rule.getNameRule() + " rule executed");
        alert.setHeaderText(null);
        if (executedAction.getExitCode() == 0) {
            alert.setContentText(executedAction.getFilePath() + " programme successfully executed");
        } else {
            alert.setContentText(executedAction.getFilePath() + " programme returned a non-zero exit code");
        }
        //alert.getButtonTypes().setAll(ButtonType.OK);
        // Show the dialog box 
        alert.show();
    }

}
