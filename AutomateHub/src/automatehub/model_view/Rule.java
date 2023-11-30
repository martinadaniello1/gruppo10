package automatehub.model_view;

import java.time.Duration;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class Rule {
    private String nameRule;
    private Action action;
    private Trigger trigger;
    private final BooleanProperty active;
    private final Boolean repeteable;
    private Duration period;

    public Rule(String nameRule, Action action, Trigger trigger, Boolean active, Boolean repeteable, Duration period) {
        this.nameRule = nameRule;
        this.action = action;
        this.trigger = trigger;
        this.active = new SimpleBooleanProperty(active);
        this.repeteable = repeteable;
        this.period= period;
    }

    public String getNameRule() {
        return nameRule;
    }

    public void setNameRule(String nameRule) {
        this.nameRule = nameRule;
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
        return active.get();
    }
    
    public BooleanProperty activeProperty(){
        return active;
    }

    public void setActive(Boolean a) {      
         this.active.set(a);
    }

    public Boolean getRepeteable() {
        return repeteable;
    }

    public Duration getPeriod() {
        return period;
    }

    public void setPeriod(Duration period) {
        this.period = period;
    }
   
    @Override
    public String toString() {
        return "La regola " + this.getNameRule() + ", azione: " + this.getAction() + ", trigger: " + this.getTrigger() + ", active: " + this.getActive();
    }
    
    
    
}
