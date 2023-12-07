package automatehub.model_view;

public class RemoveFileActionCreator implements CreatorAction{
    
    private String filePath;

    public RemoveFileActionCreator(String filePath) {
        this.filePath = filePath;
    }
    
    @Override
    public Action create() {
        return new RemoveFileAction(filePath);
    }
    
}
