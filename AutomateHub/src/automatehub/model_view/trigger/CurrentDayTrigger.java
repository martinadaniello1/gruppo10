package automatehub.model_view.trigger;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Represents the trigger that checks if the specified date is the current date.
 */
public class CurrentDayTrigger extends Trigger {

    private LocalDate date;

    public CurrentDayTrigger(LocalDate date) {
        this.date = date;
    }

    /**
     * Checks if the specified date is equal to today.
     *
     * @return true if the specified date is equal to the current date, false
     * otherwise.
     */
    @Override
    public boolean check() {
        return this.date.equals(LocalDate.now());
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String getType() {
        return "When it is this day...";
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 41 * hash + Objects.hashCode(this.date);
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
        final CurrentDayTrigger other = (CurrentDayTrigger) obj;
        if (!Objects.equals(this.date, other.date)) {
            return false;
        }
        return true;
    }

    @Override
    public String getParam1() {
        return this.getDate().toString();
    }

    @Override
    public String toString() {
        return this.getParam1();
    }

}
