package automatehub.model_view;

import java.io.File;
import java.io.IOException;
import java.util.Objects;
import org.apache.commons.io.FileUtils;

/**
 * Represents an action to copy a file to a directory. It is defined by the
 * starting path of the file and the path of the destination directory.
 */
public class CopyFileAction extends Action {

    private String startingPath;
    private String destinationPath;

    public CopyFileAction(String startingPath, String destinationPath) {
        this.startingPath = startingPath;
        this.destinationPath = destinationPath;
    }

    /**
     * Executes the action of copying the specified file to the specified
     * directory.
     *
     * @return 0 if no errors occured, -1 if an IOException is thrown.
     */
    @Override
    public int execute() {
        try {
            FileUtils.copyFileToDirectory(new File(startingPath), new File(destinationPath));
            return 0;
        } catch (IOException ex) {
            ex.printStackTrace();
            return -1;
        }
    }

    public String getStartingPath() {
        return startingPath;
    }

    public void setStartingPath(String startingPath) {
        this.startingPath = startingPath;
    }

    public String getDestinationPath() {
        return destinationPath;
    }

    public void setDestinationPath(String destinationPath) {
        this.destinationPath = destinationPath;
    }

    @Override
    public String getType() {
        return "Copy a file to a directory";
    }

    @Override
    public String getParam1() {
        return this.getStartingPath();
    }

    @Override
    public String getParam2() {
        return this.getDestinationPath();
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 11 * hash + Objects.hashCode(this.startingPath);
        hash = 11 * hash + Objects.hashCode(this.destinationPath);
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
        final CopyFileAction other = (CopyFileAction) obj;
        if (!Objects.equals(this.startingPath, other.startingPath)) {
            return false;
        }
        if (!Objects.equals(this.destinationPath, other.destinationPath)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(this.getStartingPath());
        s.append(" to ");
        s.append(this.getDestinationPath());
        return s.toString();
    }
}
