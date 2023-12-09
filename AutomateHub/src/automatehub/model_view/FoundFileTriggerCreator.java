/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package automatehub.model_view;

/**
 * The class represents a ConcreteCreator of the factory method pattern. It
 * inherit the factory method from the Creator and is responsible for the
 * creation of the new Product.
 */
public class FoundFileTriggerCreator implements CreatorTrigger {

    private String fileToSearch;
    private String referentDirectory;

    public FoundFileTriggerCreator(String fileToSearch, String referentDirectory) {
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
     * The method inherited, through which a new FoundFileTrigger will be
     * instantiated.
     *
     * @return the Trigger instantiated.
     */
    @Override
    public Trigger create() {
        return new FoundFileTrigger(fileToSearch, referentDirectory);
    }

}
