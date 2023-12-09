package automatehub.model_view;

import java.time.Duration;
import java.io.Serializable;
import java.util.Objects;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

/**
 * The {@code Rule} class represents a rule in the automation system. A rule
 * consists of a name, an associated action, a trigger, an active status, and a
 * period for repetitive execution. The rule can be associated with a thread for
 * repetitive execution.
 *
 * <p>
 * This class implements {@code Comparable} to allow sorting of rules based on
 * their names.
 *
 * <p>
 * {@code Rule} objects are used to define automation rules within the system.
 *
 */
public class Rule implements Comparable, Serializable {

    private String nameRule;
    private Action action;
    private Trigger trigger;
    private final BooleanProperty active;
    private Duration period;
    private Thread repeteable;

    public Rule(String nameRule, Action action, Trigger trigger, Boolean active, Duration period) {
        this.nameRule = nameRule;
        this.action = action;
        this.trigger = trigger;
        this.active = new SimpleBooleanProperty(active);
        this.period = period;
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

    public BooleanProperty activeProperty() {
        return active;
    }

    public void setActive(Boolean a) {
        this.active.set(a);
    }

    public Duration getPeriod() {
        return period;
    }

    public void setPeriod(Duration period) {
        this.period = period;
    }

    /**
     * Utility method to start the repeatable thread.
     */
    public void startRepeteable() {
        repeteable.start();
    }

    /**
     * Utility method to stop the repeatable thread.
     */
    public void stopRepeteable() {
        repeteable.interrupt();
    }

    @Override
    public String toString() {
        return "La regola " + this.getNameRule() + ", azione: " + this.getAction() + ", trigger: " + this.getTrigger() + ", active: " + this.getActive();
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 17 * hash + Objects.hashCode(this.nameRule);
        hash = 17 * hash + Objects.hashCode(this.action);
        hash = 17 * hash + Objects.hashCode(this.trigger);
        hash = 17 * hash + Objects.hashCode(this.active);
        hash = 17 * hash + Objects.hashCode(this.period);
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
