/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package automatehub.model_view;

import java.io.Serializable;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Objects;

/**
 *
 * @author Luca
 */
public class DayofWeekTrigger implements Trigger, Serializable {

    private DayOfWeek dayOfWeek;

    public DayofWeekTrigger(String dayInserted) {

        String dayInsertedFormatted = dayInserted.replaceAll("\\s+", "").toUpperCase();
        dayOfWeek = DayOfWeek.valueOf(dayInsertedFormatted);

    }

    @Override
    public boolean check() {

        DayOfWeek currentDayOfWeek = LocalDate.now().getDayOfWeek();

        return currentDayOfWeek.equals(dayOfWeek);
    }

    @Override
    public String getType() {
        return "When the day is ...";
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    @Override
    public String toString() {
        return this.dayOfWeek.toString();
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.dayOfWeek);
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
        final DayofWeekTrigger other = (DayofWeekTrigger) obj;
        if (this.dayOfWeek != other.dayOfWeek) {
            return false;
        }
        return true;
    }

}
