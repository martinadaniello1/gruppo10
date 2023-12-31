package automatehub.model_view.action;

import java.io.*;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Represents an Action that appends a string to a text file. The action is
 * defined by the string to append and the file path of the text file.
 */
public class AppendToFileAction extends Action {

    private String stringToAppend;
    private String filePath;

    public AppendToFileAction(String stringToAppend, String filePath) {
        this.stringToAppend = stringToAppend;
        this.filePath = filePath;
    }

    /**
     * Executes the append to file action by adding the string to the end of the
     * specified text file.
     *
     * @return 0 if the action is executed successfully, -1 if an IOException
     * occurs.
     */
    @Override
    public int execute() {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(this.getFilePath(), true));
            bw.newLine();
            bw.write(this.getStringToAppend());
            bw.close();
            return 0;
        } catch (IOException ex) {
            Logger.getLogger(AppendToFileAction.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }

    }

    public String getStringToAppend() {
        return stringToAppend;
    }

    public void setStringToAppend(String stringToAppend) {
        this.stringToAppend = stringToAppend;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + Objects.hashCode(this.stringToAppend);
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
        final AppendToFileAction other = (AppendToFileAction) obj;
        if (!Objects.equals(this.stringToAppend, other.stringToAppend)) {
            return false;
        }
        if (!Objects.equals(this.filePath, other.filePath)) {
            return false;
        }
        return true;
    }

    @Override
    public String getType() {
        return "Append a string at the end of a text file";
    }

    @Override
    public String toString() {
        return this.getStringToAppend() + " to " + this.getFilePath();
    }

    @Override
    public String getParam1() {
        return this.getStringToAppend();
    }

    @Override
    public String getParam2() {
        return this.getFilePath();
    }

}
