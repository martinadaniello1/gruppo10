package automatehub.model_view;

public class AppendToFileActionCreator implements CreatorAction {
    
    private String stringToAppend;
    private String filePath;

    public AppendToFileActionCreator(String stringToAppend, String filePath) {
        this.stringToAppend = stringToAppend;
        this.filePath = filePath;
    }

    @Override
    public Action create() {
        return new AppendToFileAction(stringToAppend, filePath);
    }
    
}
