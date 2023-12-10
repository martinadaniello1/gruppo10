package automatehub.model_view.action.creator;

import automatehub.model_view.action.Action;

/**
 * Interface for creating instances of the {@link Action} class. The interface
 * CreatorAction separates the client who is instantiating the Action from how
 * the Action will be created, through the Factory Method pattern.
 */
public interface CreatorAction {

    /**
     * Creates an instance of the {@link Action} class.
     *
     * @return a new instance of the Action class.
     */
    public Action create();

}
