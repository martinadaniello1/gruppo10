package automatehub.controller;

import automatehub.model_view.*;
import java.io.File;
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

    @Override
    public void addFileChooser(HBox box, FileExtensionFilter fileFilter) {
        Button fileChooserButton = new Button("...");
        box.getChildren().add(fileChooserButton);
        TextField tf = findTextFieldInHBox(box);

        if (fileFilter != FileExtensionFilter.DIRECTORY) {
            fileChooserButton.setOnAction(event -> {
                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Choose the file");

                if (fileFilter != FileExtensionFilter.ALL) {
                    FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
                            fileFilter.getDescription(),
                            fileFilter.getExtension()
                    );
                    fileChooser.getExtensionFilters().add(extFilter);
                }
                File selectedFile = fileChooser.showOpenDialog(box.getScene().getWindow());
                if (selectedFile != null) {
                    tf.setText(selectedFile.getAbsolutePath());
                }
            });
        } else {
            fileChooserButton.setOnAction(event -> {
                DirectoryChooser directoryChooser = new DirectoryChooser();
                directoryChooser.setTitle("Choose the directory");

                File selectedFile = directoryChooser.showDialog(box.getScene().getWindow());
                if (selectedFile != null) {
                    tf.setText(selectedFile.getAbsolutePath());
                }
            });
        }
    }

}
