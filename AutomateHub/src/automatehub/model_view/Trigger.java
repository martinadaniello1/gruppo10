package automatehub.model_view;

import java.io.Serializable;

public interface Trigger extends Serializable {
    
    public boolean check();
    public String getType();
    
}
