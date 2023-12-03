package automatehub.model_view;

public class CopyFileActionCreator implements CreatorAction {
 
    private String startingPath ;
    private String destinationPath ;

    public CopyFileActionCreator(String startingPath, String destinationPath) {
        this.startingPath = startingPath;
        this.destinationPath = destinationPath;
    }
       
    @Override
    public Action create() {
        return new CopyFileAction(startingPath, destinationPath);
    }
    
}
