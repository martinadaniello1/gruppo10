package automatehub.model_view;

public class ExitStatusTriggerCreator implements CreatorTrigger {
    private String filePath;
    private String[] commandLineArgs;
    private int exitCode;

    public ExitStatusTriggerCreator(String filePath, String[] commandLineArgs, int exitCode) {
        this.filePath = filePath;
        this.commandLineArgs = commandLineArgs;
        this.exitCode = exitCode;
    }

    @Override
    public Trigger create() {
        return new ExitStatusTrigger(filePath,commandLineArgs,exitCode);
    }
       
}
