package automatehub.model_view;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

/**
 *
 * @author martinadaniello
 */
public class DialogBoxAction implements Action {
    private String message;

    public DialogBoxAction(String message) {
        this.message = message;
    }

    @Override
    public int execute() {
        if(this.getMessage() != null) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Message");
            alert.setHeaderText(null);
            alert.setContentText(this.getMessage());
            alert.getButtonTypes().setAll(ButtonType.OK);
            // Visualizza la finestra di avviso e aspetta che l'utente la chiuda
            alert.show(); 
        }
        return 0;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return this.getMessage();
    }
   
}
