package automatehub.model_view.trigger.creator;

import automatehub.model_view.trigger.Trigger;

/**
 * Interface for creating instances of the {@link Trigger} class. The interface
 * CreatorTrigger separates the client who is instantiating the trigger from how
 * the trigger will be created, through the Factory Method pattern.
 */
public interface CreatorTrigger {

    /**
     * Creates an instance of the {@link Trigger} class.
     *
     * @return a new instance of the Trigger class.
     */
    public Trigger create();

}
