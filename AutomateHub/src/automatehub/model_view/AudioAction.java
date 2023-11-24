/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package automatehub.model_view;

import javax.sound.sampled.*;
import java.io.*;
/**
 *
 * @author mapic
 */
public class AudioAction implements Action {
    
    public String nameAction;
    public File file;
    private Clip clip;
    private Runnable atEnd;

    public AudioAction(){
        
    }
    
    public AudioAction(String nameAction, String filePath) {
        this.nameAction = nameAction;
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

    @Override
    public void setNameAction(String nameAction) {
         this.nameAction=nameAction;
    }

    @Override
    public String getNameAction() {
        return nameAction;
    }
    
    public boolean isEqual(Action o){
        if (o == null || o.getClass() != this.getClass()) {
            return false; 
        }
        AudioAction or = (AudioAction) o;
        if (this.nameAction.equals(or.getNameAction()) && this.file.equals(or.getFile())) {
            return true;
        } else {
            return false;
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
    
}
