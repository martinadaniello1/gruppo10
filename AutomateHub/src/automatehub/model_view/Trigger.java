package automatehub.model_view;

/**
 *
 * @author mapic
 */
public interface Trigger {
    
    public boolean check();
    public void setNameTrigger(String nameTrigger);
    public String getNameTrigger(); 
}
