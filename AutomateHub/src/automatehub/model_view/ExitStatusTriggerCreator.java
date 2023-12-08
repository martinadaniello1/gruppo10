package automatehub.model_view;

public class ExitStatusTriggerCreator implements CreatorTrigger {
    private String filePath;
    private int exitCode;

    public ExitStatusTriggerCreator(String filePath, int exitCode) {
        this.filePath = filePath;
        this.exitCode = exitCode;
    }

    @Override
    public Trigger create() {
        return (Trigger) new ExitStatusTrigger(filePath,exitCode);
    }
       
}
