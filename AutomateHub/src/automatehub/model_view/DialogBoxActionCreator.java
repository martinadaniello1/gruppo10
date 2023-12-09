package automatehub.model_view;

/**
 * Represents a ConcreteCreator of the Factory Method pattern for creating
 * instances of the {@link DialogBoxAction} class.
 */
public class DialogBoxActionCreator implements CreatorAction {

    private String message;

    /**
     * Constructs an DialogBoxActionCreator with the message to be displayed.
     *
     * @param message the String to be displayed.
     */
    public DialogBoxActionCreator(String message) {
        this.message = message;
    }

    /**
     * Creates an instance of the {@link DialogBoxAction} class.
     *
     * @return a new instance of the DialogBoxAction class with the specified
     * message.
     */
    @Override
    public Action create() {
        return new DialogBoxAction(message);
    }
}
