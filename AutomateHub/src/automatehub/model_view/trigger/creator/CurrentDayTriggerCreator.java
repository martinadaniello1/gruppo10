package automatehub.model_view.trigger;

import java.time.LocalDate;

/**
 * Represents a ConcreteCreator of the Factory Method pattern for creating
 * instances of the {@link CurrentDayTrigger} class.
 */
public class CurrentDayTriggerCreator implements CreatorTrigger {

    private LocalDate date;

    /**
     * Constructs a CurrentDayTriggerCreator with the specified date.
     *
     * @param date the LocalDate value of the specified date
     */
    public CurrentDayTriggerCreator(LocalDate date) {
        this.date = date;
    }

    /**
     * Creates an instance of the {@link CurrentDayTrigger} class.
     *
     * @return a new instance of the CurrentDayTrigger class with the specified
     * date.
     */
    @Override
    public Trigger create() {
        return new CurrentDayTrigger(date);
    }

}
