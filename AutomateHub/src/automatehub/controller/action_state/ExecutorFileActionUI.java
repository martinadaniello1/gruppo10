package automatehub.controller.action_state;

import automatehub.controller.ActionContext;
import automatehub.model_view.action.ExecutorFileAction;
import automatehub.model_view.FileExtensionFilter;
import automatehub.model_view.Rule;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ExecutorFileActionUI extends ActionState {

    private TextField actionTextField;
    private TextField secondTextField;
    private Label actionLabel;
    private Label secondLabel;
    private HBox hBox;
    private HBox hBox2;
    private VBox vBox;

    public ExecutorFileActionUI() {
    }

    public ExecutorFileActionUI(TextField actionTextField, TextField secondTextField, Label actionLabel, Label secondLabel, HBox hBox, HBox hBox2, VBox vBox) {
        this.actionTextField = actionTextField;
        this.secondTextField = secondTextField;
        this.actionLabel = actionLabel;
        this.secondLabel = secondLabel;
        this.hBox = hBox;
        this.hBox2 = hBox2;
        this.vBox = vBox;
    }

    @Override
    public void setupUI(ActionContext context) {
        this.actionLabel.setText("Choose a programm to run: ");
        addFileChooser(hBox, FileExtensionFilter.PYTHON);
        actionTextField.setEditable(false);
        actionTextField.focusTraversableProperty().set(false);
        vBox.getChildren().add(3, hBox2);
        hBox2.setMargin(secondLabel, new Insets(0,110,0,0));
        this.secondLabel.setText("Insert arguments: ");
        secondTextField.setPromptText("Arg1 ; Arg2 ; ... ; ArgN");
        secondTextField.focusTraversableProperty().set(false);
    }

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
