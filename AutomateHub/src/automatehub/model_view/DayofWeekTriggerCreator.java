/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package automatehub.model_view;

/**
 *
 * @author Luca
 */
public class DayOfWeekTriggerCreator implements CreatorTrigger {

    private String dayInserted;

    public DayOfWeekTriggerCreator(String dayInserted) {
        this.dayInserted = dayInserted;
    }

    @Override
    public Trigger create() {
        return new DayOfWeekTrigger(dayInserted);
    }

    public String getDayInserted() {
        return dayInserted;
    }

    public void setDayInserted(String dayInserted) {
        this.dayInserted = dayInserted;
    }

}
