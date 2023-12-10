package automatehub.controller.action_state;

import automatehub.controller.ActionContext;
import automatehub.model_view.action.AudioAction;
import automatehub.model_view.*;
import javafx.event.*;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

/**
 * This class represents the ActionState (UI) for the Action of playing an audio
 * file.
 */
public class AudioActionUI extends ActionState {

    private TextField actionTextField;
    private Label actionLabel;
    private HBox hBox;
    private Label label;

    public AudioActionUI() {
    }

    public AudioActionUI(TextField actionTextField, Label actionLabel, HBox hBox, Label label) {
        this.actionTextField = actionTextField;
        this.actionLabel = actionLabel;
        this.hBox = hBox;
        this.label = label;
    }

    /**
     * Sets up the UI elements for the AudioAction based on the provided
     * ActionContext.
     *
     * @param context The ActionContext containing information about the current
     * action.
     */
    @Override
    public void setupUI(ActionContext context) {
        label.setText(label.getText() + "\n" + "When the rule is verified, the action will be playing of a specified audio file.");

        actionLabel.setText("Insert the file audio's path: ");
        hBox.setMargin(actionLabel, new Insets(0, 10, 0, 0));
        actionTextField.setEditable(false);
        actionTextField.focusTraversableProperty().set(false);
        addFileChooser(hBox, FileExtensionFilter.WAV);
    }

    /**
     * Displays a new stage to stop the audio file.
     *
     * @param rule The rule that was executed
     */
    @Override
    public void exec(Rule rule) {
        AudioAction audioAction = (AudioAction) rule.getAction();
        VBox root = new VBox();
        Scene scene = new Scene(root, 128, 128);
        Button btn = new Button();
        btn.setText("Stop Sound");
        btn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                audioAction.stopPlaying();
            }
        });
        root.setAlignment(Pos.CENTER);
        root.setSpacing(10);
        root.getChildren().add(btn);
        Stage s = new Stage();
        s.setScene(scene);
        s.setOnCloseRequest(e -> {
            // Interrompi la riproduzione audio quando lo stage viene chiuso
            audioAction.stopPlaying();
        });
        s.show();
    }

}
