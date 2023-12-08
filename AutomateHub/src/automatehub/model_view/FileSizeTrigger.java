package automatehub.model_view;

import java.io.IOException;
import java.nio.file.*;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileSizeTrigger implements Trigger {

    private String filePath;
    private Long specifiedSize;

    public FileSizeTrigger(String filePath, Long size) {
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

    public Long getSpecifiedSize() {
        return specifiedSize;
    }

    public void setSpecifiedSize(Long specifiedSize) {
        this.specifiedSize = specifiedSize;
    }

    @Override
    public String getType() {
        return "When this file's size is bigger than this value ...";
    }

    @Override
    public String toString() {
        return "File: " + this.getFilePath() + ", specified size: " + this.getSpecifiedSize();
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.filePath);
        hash = 37 * hash + Objects.hashCode(this.specifiedSize);
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
        final FileSizeTrigger other = (FileSizeTrigger) obj;
        if (!Objects.equals(this.filePath, other.filePath)) {
            return false;
        }
        if (!Objects.equals(this.specifiedSize, other.specifiedSize)) {
            return false;
        }
        return true;
    }

}