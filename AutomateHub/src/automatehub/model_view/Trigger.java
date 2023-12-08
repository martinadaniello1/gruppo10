package automatehub.model_view;

import java.io.Serializable;

public abstract class Trigger implements Serializable {
    
    public abstract boolean check();
    public String getType(){
        return "Choose trigger";
    }
    public String getParam1(){
        return "";
    }
    public String getParam2(){
        return "";
    }
    
}
