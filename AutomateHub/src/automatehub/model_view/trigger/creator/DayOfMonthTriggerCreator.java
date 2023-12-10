package automatehub.model_view.trigger;

/**
 * Represents a ConcreteCreator of the Factory Method pattern for creating
 * instances of the {@link DayOfMonthTrigger} class.
 */
public class DayOfMonthTriggerCreator implements CreatorTrigger {
    
    private Integer dayOfMonth;

    /**
     * Constructs a DayOfMonthTriggerCreator with the specified day of month.
     * @param dayOfMonth the Integer value of the specified day of month
     */
    public DayOfMonthTriggerCreator(Integer dayOfMonth) {
        this.dayOfMonth = dayOfMonth;
    }

    /**
     * Creates an instance of the {@link DayOfMonthTrigger} class.
     *
     * @return a new instance of the DayOfMonthTrigger class with the specified
     * day of month.
     */
    @Override
    public Trigger create() {
        return new DayOfMonthTrigger(dayOfMonth);
    }
    
}
