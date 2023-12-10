package automatehub.model_view.trigger;

import java.time.DayOfWeek;

/**
 * The class represents a ConcreteCreator of the factory method pattern. It
 * inherits the factory method from the Creator and is responsible for the
 * creation of the new Product.
 *
 */
public class DayOfWeekTriggerCreator implements CreatorTrigger {

    private DayOfWeek dayInserted;

    /**
     * Constructs a DayOfWeekTriggerCreator with the specified day of month.
     *
     * @param dayInserted the Integer value of the specified day of month
     */
    public DayOfWeekTriggerCreator(DayOfWeek dayInserted) {
        this.dayInserted = dayInserted;
    }

    /**
     * The method inherited, through which a new DayOfWeekTrigger will be
     * instantiated.
     *
     * @return the Trigger instantiated.
     */
    @Override
    public Trigger create() {
        return new DayOfWeekTrigger(dayInserted);

    }

}
