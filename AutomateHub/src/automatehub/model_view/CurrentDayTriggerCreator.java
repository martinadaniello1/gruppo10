package automatehub.model_view;

import java.time.LocalDate;

public class CurrentDayTriggerCreator implements CreatorTrigger {
    
    private LocalDate date ;

    public CurrentDayTriggerCreator(LocalDate date) {
        this.date = date;
    }
   
    @Override
    public Trigger create() {
        return new CurrentDayTrigger(date);
    }
    
}
