package automatehub.model_view;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Represents a trigger that checks if the current date is equal to the
 * specified day of month.
 */
public class DayOfMonthTrigger extends Trigger {

    private Integer dayOfMonth;

    public DayOfMonthTrigger(Integer dayOfMonth) {
        this.dayOfMonth = dayOfMonth;
    }

    /**
     * Checks if the current day is equal to the specified day of month.
     *
     * @return true if the specified day of month is equal to the current date,
     * false otherwise.
     */
    @Override
    public boolean check() {
        Integer today = LocalDate.now().getDayOfMonth();
        return today.equals(this.getDayOfMonth());
    }

    public Integer getDayOfMonth() {
        return dayOfMonth;
    }

    public void setDayOfMonth(int dayOfMonth) {
        this.dayOfMonth = dayOfMonth;
    }

    @Override
    public String getType() {
        return "When it is this day of the month...";
    }

    @Override
    public String toString() {
        return "Every month on the " + this.getDayOfMonth();
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + Objects.hashCode(this.dayOfMonth);
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
        final DayOfMonthTrigger other = (DayOfMonthTrigger) obj;
        if (!Objects.equals(this.dayOfMonth, other.dayOfMonth)) {
            return false;
        }
        return true;
    }

    @Override
    public String getParam1() {
        return getDayOfMonth().toString();
    }
}
