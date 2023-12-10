package automatehub.model_view.trigger;

/**
 * The class represents a ConcreteCreator of the factory method pattern for the
 * class ExitStatusTrigger. It inherits the factory method from the Creator and
 * is responsible for the creation of the new Product.
 */
public class ExitStatusTriggerCreator implements CreatorTrigger {

    private String filePath;
    private String[] commandLineArgs;
    private int exitCode;

    /**
     * Constructs a ExitStatusTriggerCreator with the specified file path, the
     * specified command line arguments and the desired exit code.
     *
     * @param filePath the specified file path to
     * @param commandLineArgs the specified command line arguments to pass
     * @param exitCode the desired exit status
     */
    public ExitStatusTriggerCreator(String filePath, String[] commandLineArgs, int exitCode) {
        this.filePath = filePath;
        this.commandLineArgs = commandLineArgs;
        this.exitCode = exitCode;
    }

    /**
     *
     * Creates an instance of the {@link ExitStatusTrigger} class.
     *
     * @return a new instance of the ExitStatusTrigger class with the specified
     * file path, command line args and exit code.
     */
    @Override
    public Trigger create() {
        return new ExitStatusTrigger(filePath, commandLineArgs, exitCode);
    }

}
