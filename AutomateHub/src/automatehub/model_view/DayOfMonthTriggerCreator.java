package automatehub.model_view;

public class DayOfMonthTriggerCreator implements CreatorTrigger {
    
    private Integer dayOfMonth;

    public DayOfMonthTriggerCreator(Integer dayOfMonth) {
        this.dayOfMonth = dayOfMonth;
    }

    @Override
    public Trigger create() {
        return new DayOfMonthTrigger(dayOfMonth);
    }
    
}
