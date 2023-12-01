package automatehub.model_view;

import java.io.Serializable;
import java.util.Objects;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;


public class Rule implements Comparable, Serializable{

    private String nameRule;
    private Action action;
    private Trigger trigger;
    private final BooleanProperty active;

    public Rule(String nameRule, Action action, Trigger trigger, Boolean active) {
        this.nameRule = nameRule;
        this.action = action;
        this.trigger = trigger;
        this.active = new SimpleBooleanProperty(active);
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

    @Override
    public String toString() {
        return "La regola " + this.getNameRule() + ", azione: " + this.getAction() + ", trigger: " + this.getTrigger() + ", active: " + this.getActive();
    }

    @Override
    public int hashCode() {
        int hash = 7;
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
        final Rule other = (Rule) obj;
        if (!Objects.equals(this.nameRule, other.nameRule)) {
            return false;
        }
        if (!Objects.equals(this.action, other.action)) {
            return false;
        }
        if (!Objects.equals(this.trigger, other.trigger)) {
            return false;
        }
        if (!Objects.equals(this.active, other.active)) {
            return false;
        }
        return true;
    }
    
    @Override
    public int compareTo(Object o) {
        Rule otherRule = (Rule) o;
        return this.nameRule.compareTo(otherRule.getNameRule());
    }
    

}
