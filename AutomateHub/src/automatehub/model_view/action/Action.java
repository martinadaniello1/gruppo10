package automatehub.model_view.action;

import java.io.Serializable;

/**
 * The base abstract class for defining actions in the AutomateHub system.
 * Actions represent executable tasks and provide a framework for specific
 * action implementations. Subclasses of this class must implement the
 * {@code execute()} method to define the action's behavior.
 *
 * @see Serializable
 */
public abstract class Action implements Serializable {

    /**
     * Executes the action and returns the result.
     *
     * @return an integer representing the result of the action execution.
     */
    public abstract int execute();

    /**
     * Gets the type of the action.
     *
     * @return a string representing the type of the action.
     */
    public String getType() {
        return "Choose action";
    }

    /**
     * Utility method used to retrieve action's attributes as Strings.
     *
     * @return a String representing the first attribute of the action.
     */
    public String getParam1() {
        return "";
    }

    /**
     * Utility method used to retrieve action's attributes as Strings.
     *
     * @return a String representing the second attribute of the action.
     */
    public String getParam2() {
        return "";
    }
}
