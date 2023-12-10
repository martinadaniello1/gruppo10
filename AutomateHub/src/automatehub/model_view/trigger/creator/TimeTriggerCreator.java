package automatehub.model_view.trigger.creator;

import automatehub.model_view.trigger.*;

import java.time.LocalTime;

/**
 * The class represents a ConcreteCreator of the factory method pattern. It
 * inherits the factory method from the Creator and is responsible for the
 * creation of the new Product.
 *
 */
public class TimeTriggerCreator implements CreatorTrigger {

    private LocalTime timeInserted;

    public TimeTriggerCreator(LocalTime timeInserted) {
        this.timeInserted = timeInserted;
    }

    /**
     * The method inherited, through which a new TimeTrigger will be
     * instantiated.
     *
     * @return the Trigger instantiated.
     */
    @Override
    public Trigger create() {
        return new TimeTrigger(timeInserted);
    }
}
