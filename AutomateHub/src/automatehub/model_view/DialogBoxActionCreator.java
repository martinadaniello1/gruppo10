package automatehub.model_view;

public class DialogBoxActionCreator implements CreatorAction {
    
    private String message;
    
    public DialogBoxActionCreator(String message) {
        this.message = message;
    }

    @Override
    public Action create() {
        return new DialogBoxAction(message);
    }
}
