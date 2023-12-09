package automatehub.model_view;

import java.io.Serializable;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

/**
 * The class represents a trigger whose condition is verified when the search
 * for a file in a specified directory is satisfied.
 *
 */
public class FoundFileTrigger extends Trigger {

    private String fileToSearch;
    private String referentDirectory;

    public FoundFileTrigger(String fileToSearch, String referentDirectory) {
        this.fileToSearch = fileToSearch;
        this.referentDirectory = referentDirectory;
    }

    public String getFileToSearch() {
        return fileToSearch;
    }

    public void setFileToSearch(String fileToSearch) {
        this.fileToSearch = fileToSearch;
    }

    public String getReferentDirectory() {
        return referentDirectory;
    }

    public void setReferentDirectory(String referentDirectory) {
        this.referentDirectory = referentDirectory;
    }

    /**
     * The function is responsible for the validation of trigger condition.
     *
     * @return True if the condition is verified, False otherwise.
     */
    @Override
    public boolean check() {
        Path directoryPath = FileSystems.getDefault().getPath(this.referentDirectory, this.fileToSearch);
        return !Files.notExists(directoryPath);
    }

    @Override
    public String getType() {
        return "Found a file in a directory...";
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + Objects.hashCode(this.fileToSearch);
        hash = 71 * hash + Objects.hashCode(this.referentDirectory);
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
        final FoundFileTrigger other = (FoundFileTrigger) obj;
        if (!Objects.equals(this.fileToSearch, other.fileToSearch)) {
            return false;
        }
        if (!Objects.equals(this.referentDirectory, other.referentDirectory)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Searching file " + getFileToSearch() + " in " + getReferentDirectory();
    }

    @Override
    public String getParam1() {
        return getFileToSearch();
    }

    @Override
    public String getParam2() {
        return getReferentDirectory();
    }

}
