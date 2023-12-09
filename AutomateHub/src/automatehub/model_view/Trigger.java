package automatehub.model_view;

import java.io.Serializable;

/**
 *
 * The class represents a generic Trigger.
 */
public abstract class Trigger implements Serializable {

    public abstract boolean check();

    /**
     * The method represents the type of trigger istantiating.
     *
     * @return a String representing the trigger type.
     */
    public String getType() {
        return "Choose trigger";
    }

    /**
     * These are utility methods used to retrieve triggers attributes as
     * Strings.
     *
     * @return a String representing a specific attribute of the trigger.
     */
    public String getParam1() {
        return "";
    }

    public String getParam2() {
        return "";
    }

    public String getParam3() {
        return "";
    }

}
