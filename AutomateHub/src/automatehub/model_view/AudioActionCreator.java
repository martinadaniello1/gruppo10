/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package automatehub.model_view;

/**
 *
 * @author mapic
 */
public class AudioActionCreator implements CreatorAction{
    private String pathFile;

    public AudioActionCreator(String[] pathFile) {
        this.pathFile = pathFile[0];
    }

    @Override
    public Action create() {
        return new AudioAction(pathFile);
    }

}
