package automatehub.model_view;

/**
 *
 * @author mapic
 */

public class Rule {
    public String nameRule;
    private Action action;
    private Trigger trigger;
    private Boolean active;

    public Rule(){
       
    }
    
    public Rule(String nameRule, Action action, Trigger trigger, Boolean active) {
        this.nameRule = nameRule;
        this.action = action;
        this.trigger = trigger;
        this.active = active;
    }
    

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public Trigger getTrigger() {
        return trigger;
    }

    public void setTrigger(Trigger trigger) {
        this.trigger = trigger;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "La regola " + nameRule + " è composta dal trigger " + trigger.getNameTrigger() + " e dall'azione " + action.getNameAction();
    }   
    
    
}
