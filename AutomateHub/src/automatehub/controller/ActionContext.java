package automatehub.controller;

import automatehub.model_view.Rule;

/**
 * The ActionContext class communicates with the state objects via the reference
 * to the ActionState class.
 */
public class ActionContext {

    private ActionState currentState;

    /**
     * This method changes the current state.
     *
     * @param actionState The new state to set for the action. This 
     * should be an implementation of the ActionState abstract class.
     */
    public void changeState(ActionState actionState) {
        this.currentState = actionState;
    }

    /**
     * This method calls the setupUI method of the current State object.
     */
    public void setupUI() {
        currentState.setupUI(this);
    }

    /**
     * This method calls the exec method of the current State object.
     *
     * @param rule The Rule object to execute.
     */
    public void exec(Rule rule) {
        currentState.exec(rule);
    }
}
