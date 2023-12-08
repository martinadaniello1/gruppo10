package automatehub.controller;

import automatehub.model_view.*;
import java.io.File;
import java.util.Optional;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * This class is the ActionState for the Action of playing an audio file.
 */
public class AudioActionUI extends ActionState {

    private TextField actionTextField;
    private Label actionLabel;
    private HBox hBox;

    public AudioActionUI() {
    }

    public AudioActionUI(TextField actionTextField, Label actionLabel, HBox hBox) {
        this.actionTextField = actionTextField;
        this.actionLabel = actionLabel;
        this.hBox = hBox;
    }

    @Override
    public void setupUI(ActionContext context) {
        actionLabel.setText("Insert the file audio's path:");
        actionTextField.setEditable(false);
        actionTextField.focusTraversableProperty().set(false);
        addFileChooser(hBox, FileExtensionFilter.WAV);
    }

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
        root.setAlignment(Pos.CENTER); // Center aligns its children
        root.setSpacing(10); // You can adjust the spacing as needed
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
