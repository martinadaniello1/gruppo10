/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package automatehub.model_view;

/**
 *
 * @author mapic
 */
public class DialogBoxActionCreator implements CreatorAction {
    private String message;

    public DialogBoxActionCreator(String[] message) {
        this.message = message[0];
    }

    public DialogBoxActionCreator(String message) {
        this.message = message;
    }

    @Override
    public Action create() {
        return new DialogBoxAction(message);
    }
}
