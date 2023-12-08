/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package automatehub.model_view;

import java.io.Serializable;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

/**
 *
 * @author Luca
 */
public class FoundFileTrigger implements Trigger, Serializable {

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

    @Override
    public boolean check() {
        Path directoryPath = FileSystems.getDefault().getPath(this.referentDirectory, this.fileToSearch);
        return !Files.notExists(directoryPath);
    }

    @Override
    public String getType() {
        return "Found a file in a directory ...";
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
        return this.fileToSearch;
    }

}
