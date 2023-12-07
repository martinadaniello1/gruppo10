package automatehub.model_view;

import java.io.IOException;
import java.nio.file.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileSizeTrigger implements Trigger {

    private String filePath;
    private Integer specifiedSize;

    public FileSizeTrigger(String filePath, Integer size) {
        this.filePath = filePath;
        this.specifiedSize = size;
    }

    @Override
    public boolean check() {
        Path path = Paths.get(this.getFilePath());
        try {
            if (Files.size(path) > this.getSpecifiedSize()) {
                return true;
            } else {
                return false;
            }
        } catch (IOException ex) {
            Logger.getLogger(FileSizeTrigger.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public Integer getSpecifiedSize() {
        return specifiedSize;
    }

    public void setSpecifiedSize(Integer specifiedSize) {
        this.specifiedSize = specifiedSize;
    }

    @Override
    public String getType() {
        return "When this file's size is bigger than this value ...";
    }

}
