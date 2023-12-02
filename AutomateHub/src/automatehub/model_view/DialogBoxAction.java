package automatehub.model_view;

import java.io.Serializable;
import java.util.Objects;


public class DialogBoxAction implements Action, Serializable {
    
    private String message;

    public DialogBoxAction(String message) {
        
        this.message = message;
    }

    @Override
    public int execute() {
        //Setting up the alert message to show
        
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
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + Objects.hashCode(this.message);
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
        final DialogBoxAction other = (DialogBoxAction) obj;
        if (!Objects.equals(this.message, other.message)) {
            return false;
        }
        return true;
    }
   
    
}
