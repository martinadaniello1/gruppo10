package automatehub.model_view;

import java.io.Serializable;
import java.util.Objects;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

/**
 *
 * @author martinadaniello
 */
public class DialogBoxAction implements Action, Serializable {
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
    
    public String getType() {
        return "Show a message";
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return this.getMessage();
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
        final DialogBoxAction other = (DialogBoxAction) obj;
        if (!Objects.equals(this.message, other.message)) {
            return false;
        }
        return true;
    }
   
    
}
