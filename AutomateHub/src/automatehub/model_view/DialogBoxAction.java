package automatehub.model_view;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

/**
 *
 * @author martinadaniello
 */
public class DialogBoxAction implements Action {
    private String nameAction;
    private String message;

    public DialogBoxAction(String nameAction, String message) {
        this.nameAction = nameAction;
        this.message = message;
    }

    @Override
    public int execute() {
        if(this.getMessage() != null) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Messaggio");
            alert.setHeaderText(null);
            alert.setContentText(this.getMessage());
            alert.getButtonTypes().setAll(ButtonType.OK);
            // Visualizza la finestra di avviso e aspetta che l'utente la chiuda
            alert.showAndWait(); 
        }
        return 0;
    }

    @Override
    public void setNameAction(String nameAction) {
        this.nameAction = nameAction;
    }

    @Override
    public String getNameAction() {
        return this.nameAction;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    
}
