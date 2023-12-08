package automatehub.model_view;

import java.time.LocalTime;

public class TimeTriggerCreator implements CreatorTrigger {
    
    private LocalTime timeInserted;

    public TimeTriggerCreator(LocalTime timeInserted) {
        this.timeInserted = timeInserted;
    }

    @Override
    public Trigger create() {
        return new TimeTrigger(timeInserted);
    }
}
