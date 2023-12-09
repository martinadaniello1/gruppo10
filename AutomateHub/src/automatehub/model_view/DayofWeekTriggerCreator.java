package automatehub.model_view;

/**
 * The class represents a ConcreteCreator of the factory method pattern. It
 * inherit the factory method from the Creator and is responsible for the
 * creation of the new Product.
 *
 */
public class DayOfWeekTriggerCreator implements CreatorTrigger {

    private String dayInserted;

    public DayOfWeekTriggerCreator(String dayInserted) {
        this.dayInserted = dayInserted;
    }

    /**
     * The method inherited, through which a new DayofWeekTrigger will be
     * instantiated.
     *
     * @return the Trigger instantiated.
     */
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
