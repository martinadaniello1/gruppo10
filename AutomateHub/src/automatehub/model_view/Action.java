package automatehub.model_view;

/**
 *
 * @author mapic
 */

public interface Action {

    public int execute();
    public void setNameAction(String nameAction);    
    public String getNameAction();    
}
