package automatehub.controller;

/**
 * The TriggerContext class communicates with the state objects via the
 * reference to the TriggerState class.
 */
public class TriggerContext {

    private TriggerState currentState;

    /**
     * This method changes the current State.
     *
     * @param triggerState The new state to set for the trigger. This should be
     * an implementation of the TriggerState abstract class.
     */
    public void changeState(TriggerState triggerState) {
        this.currentState = triggerState;
    }

    /**
     * This method calls the setupUI method of the current State object.
     */
    public void setupUI() {
        currentState.setupUI(this);
    }
}
