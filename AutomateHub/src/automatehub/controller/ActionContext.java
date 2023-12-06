package automatehub.controller;

import automatehub.model_view.Rule;

public class ActionContext {
    private ActionState currentState;

    public void changeState(ActionState actionState) {
        this.currentState = actionState;
    }
    
    public void setupUI() {
        currentState.setupUI(this);
    }
   
    public void exec(Rule rule){
        currentState.exec(rule);
    }
}
