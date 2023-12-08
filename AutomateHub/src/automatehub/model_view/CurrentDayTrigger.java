package automatehub.model_view;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class CurrentDayTrigger extends Trigger {

    private LocalDate date;

    public CurrentDayTrigger(LocalDate date) {
        this.date = date;
    }

    @Override
    public boolean check() {
        if (this.date.equals(LocalDate.now())) {
            return true;
        } else {
            return false;
        }
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
