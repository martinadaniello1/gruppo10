package automatehub.controller;

import automatehub.model_view.CreatorAction;

public class ActionContext {
    private ActionState currentState;

    public void changeState(ActionState actionState) {
        this.currentState = actionState;
    }
    
    public void setupUI() {
        currentState.setupUI(this);
    }
    
    public CreatorAction getCreator() {
        return currentState.getCreator(this);
    }
   
}
