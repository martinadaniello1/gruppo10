package automatehub.model_view;

/**
 * Represents a ConcreteCreator of the Factory Method pattern for creating
 * instances of the {@link CopyFileAction} class.
 */
public class CopyFileActionCreator implements CreatorAction {

    private String startingPath;
    private String destinationPath;

    /**
     * Constructs an CopyFileActionCreator with the specified file to copy and
     * the specified directory in which to copy.
     *
     * @param startingPath the specified file path to copy
     * @param destinationPath the specified directory in which to copy
     */
    public CopyFileActionCreator(String startingPath, String destinationPath) {
        this.startingPath = startingPath;
        this.destinationPath = destinationPath;
    }

    /**
     * Creates an instance of the {@link CopyFileAction} class.
     *
     * @return a new instance of the CopyFileAction class with the specified
     * file to copy and the directory in which to copy.
     */
    @Override
    public Action create() {
        return new CopyFileAction(startingPath, destinationPath);
    }

}
