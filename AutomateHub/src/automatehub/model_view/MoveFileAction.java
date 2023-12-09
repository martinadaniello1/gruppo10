package automatehub.model_view;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.io.FileUtils;

/**
 * Represents the action of moving a file to another specified directory. It is
 * defined by a starting path where the file is located and a destination path
 * for the directory in which to move the file.
 */
public class MoveFileAction extends Action {

    private String startingPath;
    private String destinationPath;

    public MoveFileAction(String startingPath, String destinationPath) {
        this.startingPath = startingPath;
        this.destinationPath = destinationPath;
    }

    /**
     * Executes the action of moving the file to the specified directory.
     * @return 0 if the action was successful, -1 otherwise.
     */
    @Override
    public int execute() {
        try {
            FileUtils.moveFileToDirectory(new File(startingPath), new File(destinationPath), false);
            return 0;
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            return -1;
        } catch (IOException ex) {
            Logger.getLogger(MoveFileAction.class.getName()).log(Level.SEVERE, null, ex);
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
        return "Move a file from a directory";
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(this.getStartingPath());
        s.append(" to ");
        s.append(this.getDestinationPath());
        return s.toString();
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
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.startingPath);
        hash = 97 * hash + Objects.hashCode(this.destinationPath);
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
        final MoveFileAction other = (MoveFileAction) obj;
        if (!Objects.equals(this.startingPath, other.startingPath)) {
            return false;
        }
        if (!Objects.equals(this.destinationPath, other.destinationPath)) {
            return false;
        }
        return true;
    }

}
