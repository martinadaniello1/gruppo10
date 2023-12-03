package automatehub.model_view;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.io.FileUtils;
/**
 *
 * @author adc01
 */
public class CopyFileAction implements Action{
    private String startingPath ;
    private String destinationPath ;

    public CopyFileAction(String startingPath, String destinationPath) {
        this.startingPath = startingPath;
        this.destinationPath = destinationPath;
    }

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
    public String toString(){
        StringBuilder s = new StringBuilder();
        s.append(this.getStartingPath());
        s.append(" to ");
        s.append(this.getDestinationPath());
        return s.toString();
    }
    
}