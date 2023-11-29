/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package automatehub.model_view;

/**
 *
 * @author mapic
 */
public class TimeTriggerCreator implements CreatorTrigger {
    private String timeInserted;

    public TimeTriggerCreator(String[] timeInserted) {
        this.timeInserted = timeInserted[0];
    }

    public TimeTriggerCreator(String timeInserted) {
        this.timeInserted = timeInserted;
    }
    

    @Override
    public Trigger create() {
        return new TimeTrigger(timeInserted);
    }
}
