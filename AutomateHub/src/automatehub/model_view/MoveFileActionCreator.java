package automatehub.model_view;

public class MoveFileActionCreator implements CreatorAction {
 
    private String startingPath ;
    private String destinationPath ;

    public MoveFileActionCreator(String startingPath, String destinationPath) {
        this.startingPath = startingPath;
        this.destinationPath = destinationPath;
    }
       
    @Override
    public Action create() {
        return new MoveFileAction(startingPath, destinationPath);
    }
    
}
