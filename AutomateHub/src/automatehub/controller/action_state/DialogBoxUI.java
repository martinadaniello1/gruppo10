package automatehub.controller.action_state;

import automatehub.controller.ActionContext;
import automatehub.model_view.action.DialogBoxAction;
import automatehub.model_view.Rule;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;

/**
 * This class is the ActionState for the Action of showing a message.
 */
public class DialogBoxUI extends ActionState {

    private Label actionLabel;
    private Label label;

    public DialogBoxUI() {
    }

    public DialogBoxUI(Label actionLabel, Label label) {
        this.actionLabel = actionLabel;
        this.label = label;
    }

    /**
     * Sets up the UI elements for the DialogBoxAction based on the provided
     * ActionContext.
     *
     * @param context The ActionContext containing information about the current
     * action.
     */
    @Override
    public void setupUI(ActionContext context) {
        this.actionLabel.setText("Insert the text to display: ");
        label.setText(label.getText() + "\n" + "When the rule is verified, the action will be showing a specified message in a dialog box, that must be explicitly closed by the user.");
        System.out.println(label.getText());
    }

    /**
     * Displays the message of the DialogBoxAction.
     *
     * @param rule the rule to be executed
     */
    @Override
    public void exec(Rule rule) {
        DialogBoxAction action = (DialogBoxAction) rule.getAction();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        if (action.getMessage() != null) {
            alert.setTitle(rule.getNameRule() + " rule executed");
            alert.setHeaderText(null);
            alert.setContentText(action.getMessage());
            alert.getButtonTypes().setAll(ButtonType.OK);
            // Show the dialog box 
            alert.show();
        }
    }

}
