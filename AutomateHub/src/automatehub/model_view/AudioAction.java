package automatehub.model_view;

import javax.sound.sampled.*;
import java.io.*;
import java.util.Objects;

public class AudioAction implements Serializable, Action {

    private File file;
    // The Clip object is not serialisable, 
    // the transient modifier indicates this object must be excluded from serialisation
    private transient Clip clip;
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

    public String getType() {
        return "Play an audio file";
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
                    if (r != null) {
                        r.run();
                    }
                }
            });
            startPlaying(getAtEnd());

            return 0;

        } catch (LineUnavailableException exc) {
            System.out.printf("Sorry. Cannot play audio files.");
            return -1;
        } catch (UnsupportedAudioFileException exc) {
            System.out.printf("Unsupported file format for: " + file);
            return -1;
        } catch (FileNotFoundException exc) {
            System.out.printf("File not found: " + file);
            return -1;
        } catch (IOException exc) {
            System.out.printf("IOException: " + exc);
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

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final AudioAction other = (AudioAction) obj;
        if (!Objects.equals(this.file, other.file)) {
            return false;
        }
        if (!Objects.equals(this.clip, other.clip)) {
            return false;
        }
        return true;
    }

    @Override
    public String getParam1() {
        return this.getFile().getAbsolutePath();
    }

    @Override
    public String getParam2() {
        return "";
    }

}
