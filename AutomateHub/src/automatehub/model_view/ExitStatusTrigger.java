package automatehub.model_view;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class ExitStatusTrigger extends Trigger{

    private String filePath;
    private Integer exitCodeDesired;

    public ExitStatusTrigger(String filePath, Integer exitCode) {
        this.filePath = filePath;
        this.exitCodeDesired = exitCode;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public Integer getExitCodeDesired() {
        return exitCodeDesired;
    }

    public void setExitCodeDesired(Integer exitCodeDesired) {
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

    @Override
    public String getParam1() {
        return this.getFilePath();
    }

    @Override
    public String getParam2() {
        return this.getExitCodeDesired().toString();
    }

}
