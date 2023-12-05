/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package automatehub.controller;

import automatehub.model_view.DialogBoxAction;
import automatehub.model_view.Rule;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;

/**
 *
 * @author mapic
 */
public class DialogBoxUI extends ActionState {
    private Label actionLabel;

    public DialogBoxUI() {
    }

    
    public DialogBoxUI(Label actionLabel) {
        this.actionLabel = actionLabel;
    }    
    
    @Override
    public void setupUI(ActionContext context) {
        this.actionLabel.setText("Insert the text to display:");    
    }
    
    @Override
    public void exec(Rule rule){
        DialogBoxAction action = (DialogBoxAction) rule.getAction();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);        
        if (action.getMessage() != null) {
            alert.setTitle(rule.getNameRule() + " rule executed");
            alert.setHeaderText(null);
            alert.setContentText(action.getMessage());
            alert.getButtonTypes().setAll(ButtonType.OK);
            // Show the dialog box 
            alert.show();
        }
    }
    
}
