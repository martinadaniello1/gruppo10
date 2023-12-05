/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package automatehub.controller;

import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

/**
 *
 * @author mapic
 */
public abstract class TriggerState {
    public abstract void setupUI(TriggerContext context);
    
    /*
    public TextField findTextFieldInHBox(HBox hbox) {
        for (Node node : hbox.getChildren()) {
            if (node instanceof TextField) {
                return (TextField) node;
            }
        }
        return null;
    }*/
}
