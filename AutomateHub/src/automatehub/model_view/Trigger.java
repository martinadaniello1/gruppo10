package automatehub.model_view;

import java.io.Serializable;

/**
 *
 * The base abstract class for defining trigger in the AutomateHub system.
 * Triggers represent conditions to be verified. Subclasses of this class must
 * implement the {@code check()} method to define the trigger's behavior.
 *
 * @see Serializable
 */
public abstract class Trigger implements Serializable {

    public abstract boolean check();

    /**
     * The method represents the type of trigger instantiated.
     *
     * @return a String representing the trigger type.
     */
    public String getType() {
        return "Choose trigger";
    }

    /**
     * Utility method used to retrieve trigger's attributes as Strings.
     *
     * @return a String representing the first attribute of the trigger.
     */
    public String getParam1() {
        return "";
    }

    /**
     * Utility method used to retrieve trigger's attributes as Strings.
     *
     * @return a String representing the second attribute, if present, of the
     * trigger.
     */
    public String getParam2() {
        return "";
    }

    /**
     * Utility method used to retrieve trigger's attributes as Strings.
     *
     * @return a String representing the third attribute, if present, of the
     * trigger.
     */
    public String getParam3() {
        return "";
    }

}
