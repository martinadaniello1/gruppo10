package automatehub.model_view;

public class TimeTriggerCreator implements CreatorTrigger {
    
    private String timeInserted;

    public TimeTriggerCreator(String timeInserted) {
        this.timeInserted = timeInserted;
    }
    

    @Override
    public Trigger create() {
        return new TimeTrigger(timeInserted);
    }
}
