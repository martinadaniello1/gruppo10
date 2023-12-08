package automatehub.model_view;

import java.io.Serializable;

public interface Action extends Serializable {

    public int execute();
    public String getType();
    public String getParam1();
    public String getParam2();
}
