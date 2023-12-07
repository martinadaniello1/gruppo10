package automatehub.model_view;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ExitStatusTrigger implements Trigger, Serializable {

    private String filePath;
    private int exitCodeDesired;

    public ExitStatusTrigger(String filePath, int exitCode) {
        this.filePath = filePath;
        this.exitCodeDesired = exitCode;
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
        File f = new File(filePath);
        if (!f.exists()) {
            return false;
        } else {
            try {
                // execute the program
                Process process = Runtime.getRuntime().exec(filePath);

                // wait for the result
                int exitCode = process.waitFor();

                // check the result
                if (exitCode == exitCodeDesired) {
                    return true;
                } else {
                    return false;
                }

            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public String getType() {
        return "When the program returns...";
    }

}
