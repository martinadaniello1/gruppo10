package automatehub.model_view.trigger;

import automatehub.model_view.trigger.Trigger;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

/**
 * The class represents a trigger whose condition is verified when the current
 * time of the day corresponds to the inserted time.
 */
public class TimeTrigger extends Trigger {

    private LocalTime time;

    public TimeTrigger(LocalTime time) {
        this.time = time.truncatedTo(ChronoUnit.MINUTES);
    }

    @Override
    public String getType() {
        return "When the clock hits...";
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    /**
     * The function is responsible for the validation of trigger condition.
     *
     * @return True if the condition is verified, False otherwise.
     */
    @Override
    public boolean check() {

        LocalTime actualTime = LocalTime.now().truncatedTo(ChronoUnit.MINUTES);

        return actualTime.equals(this.time);
    }

    @Override
    public String toString() {
        return this.getTime().toString();
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
        final TimeTrigger other = (TimeTrigger) obj;
        if (!Objects.equals(this.time, other.time)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.time);
        return hash;
    }

    @Override
    public String getParam1() {
        return this.getTime().format(DateTimeFormatter.ofPattern("HH:mm"));
    }

}
