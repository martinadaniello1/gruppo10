package automatehub.model_view;

/**
 * Represents a ConcreteCreator of the Factory Method pattern for creating
 * instances of the {@link ExecutorFileAction} class.
 */
public class ExecutorFileActionCreator implements CreatorAction {

    private String filePath;
    private String[] arguments;

    /**
     * Constructs an ExecutorFileActionCreator with the specified file path and
     * arguments.
     *
     * @param filePath the file path of the external program.
     * @param arguments the command line arguments.
     */
    public ExecutorFileActionCreator(String filePath, String[] arguments) {
        this.filePath = filePath;
        this.arguments = arguments;
    }

    /**
     * Creates an instance of the {@link ExecutorFileAction} class.
     *
     * @return a new instance of the ExecutorFileAction class with the specified
     * file path and arguments.
     */
    @Override
    public Action create() {
        return new ExecutorFileAction(filePath, arguments);
    }

}
