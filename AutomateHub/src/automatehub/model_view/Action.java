package automatehub.model_view;

import java.io.Serializable;

public abstract class Action implements Serializable {

    public abstract int execute();
    
    public String getType(){
        return "Choose action";
    }
    public String getParam1(){
        return "";
    }
    public String getParam2(){
        return "";
    }
}
