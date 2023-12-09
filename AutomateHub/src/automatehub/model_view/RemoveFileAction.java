package automatehub.model_view;

import java.io.File;
import java.util.Objects;

/**
 * Represents the action that removes a specified file.
 */
public class RemoveFileAction extends Action {

    private String filePath;

    /**
     * Executes the action of removing the specified file. 
     * @return 0 if the removal was successful, -1 otherwise.
     */
    @Override
    public int execute() {
        File f = new File(this.filePath);
        if (f.delete()) {
            System.out.println("Rimozione del file " + f.getAbsolutePath() + " avvenuta con successo");
            return 0;
        } else {
            System.out.println("Rimozione fallita del file " + f.getAbsolutePath());
            return -1;
        }
    }

    @Override
    public String getType() {
        return "Remove a file from a directory";
    }

    public RemoveFileAction(String filePath) {
        this.filePath = filePath;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public String getParam1() {
        return this.getFilePath();
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("Remove the ");
        s.append(this.getFilePath());
        return s.toString();
    }

    @Override
    public int hashCode() {
        int hash = 7;
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
        final RemoveFileAction other = (RemoveFileAction) obj;
        if (!Objects.equals(this.filePath, other.filePath)) {
            return false;
        }
        return true;
    }

}
