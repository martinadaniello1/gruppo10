/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package automatehub.model_view;

import javax.sound.sampled.*;
import java.io.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import static javafx.scene.control.ContentDisplay.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javax.sound.sampled.*;
        
public class AudioAction implements Action {
    
    public File file;
    private Clip clip;
    private Runnable atEnd;

    
    public AudioAction(String filePath) {
        this.file = new File(filePath);
    }  

    public File getFile() {
        return file;
    }

    public void setFile(String filePath) {
        this.file = new File(filePath);
    }

    public Clip getClip() {
        return clip;
    }

    public void setClip(Clip clip) {
        this.clip = clip;
    }

    public Runnable getAtEnd() {
        return atEnd;
    }

    public void setAtEnd(Runnable atEnd) {
        this.atEnd = atEnd;
    }
    
    @Override
    public int execute() {
        try {
            VBox root = new VBox();
            Scene scene = new Scene(root, 128, 128);
            
            clip = AudioSystem.getClip();
            AudioInputStream ais = AudioSystem.getAudioInputStream(file);

            clip.open(ais);
            atEnd = null;

            clip.addLineListener(evt -> {
                if (evt.getType().equals(LineEvent.Type.STOP)) {
                    Runnable r = getAtEnd();
                    if (r != null)
                        r.run();
                }
            });
            startPlaying(getAtEnd());
            
            Button btn = new Button();
            btn.setText("Stop Sound");
            btn.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) {
                    stopPlaying();
                }
            });
            root.setAlignment(Pos.CENTER); // Center aligns its children
            root.setSpacing(10); // You can adjust the spacing as needed
            root.getChildren().add(btn);
            Stage s = new Stage();
            s.setScene(scene);
            s.show();
            
            return 0;

        } catch (LineUnavailableException exc) {
            System.out.printf("Sorry. Cannot play audio files.");
            return -1;
        } catch (UnsupportedAudioFileException exc) {
            System.out.printf("Unsupported file format for: "+ file);
            return -1;
        } catch (FileNotFoundException exc) {
            System.out.printf("File not found: "+file);
            return -1;
        } catch (IOException exc) {
            System.out.printf("IOException: "+exc);
            return -1;
        }
    }
    
    public void startPlaying(Runnable atEnd) {
        setAtEnd(atEnd);
        clip.start();
    }

    public void stopPlaying() {
        setAtEnd(null);
        clip.stop();
    }
    
    @Override
    public String toString() {
        return this.getFile().getPath();
    }

}
