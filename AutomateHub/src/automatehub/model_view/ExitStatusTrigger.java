package automatehub.model_view;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ExitStatusTrigger implements Trigger {

    private String filePath;
    private final String suffix = "-output";
    private int exitCodeDesired;
    private File statusFile;

    public ExitStatusTrigger(String filePath, int exitCode) {
        this.filePath = filePath + suffix;
        this.exitCodeDesired = exitCode;
        statusFile = new File(this.filePath);
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public int getExitCodeDesired() {
        return exitCodeDesired;
    }

    public void setExitCodeDesired(int exitCodeDesired) {
        this.exitCodeDesired = exitCodeDesired;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
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
        final ExitStatusTrigger other = (ExitStatusTrigger) obj;
        if (!Objects.equals(this.filePath, other.filePath)) {
            return false;
        }
        return true;
    }

    @Override
    public boolean check() {
        if (!statusFile.exists()) {
            return false;
        } else {
            try {
                String exitStatusString = new String(Files.readAllBytes(statusFile.toPath()));
                int exitStatus = Integer.parseInt(exitStatusString);
                statusFile.delete();
                if (exitStatus == exitCodeDesired) {
                    return true;
                } else {
                    return false;
                }
            } catch (IOException ex) {
                Logger.getLogger(ExitStatusTrigger.class.getName()).log(Level.SEVERE, null, ex);
            }
            return false;
        }
    }

    @Override
    public String getType() {
        return "When the program returns...";
    }

}
