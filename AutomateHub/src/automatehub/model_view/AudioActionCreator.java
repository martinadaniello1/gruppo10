package automatehub.model_view;

public class AudioActionCreator implements CreatorAction{
    private String pathFile;

    public AudioActionCreator(String pathFile) {
        this.pathFile = pathFile;
    }
    
    @Override
    public Action create() {
        return new AudioAction(pathFile);
    }

}
