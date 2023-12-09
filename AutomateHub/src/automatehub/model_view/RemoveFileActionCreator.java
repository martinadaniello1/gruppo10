package automatehub.model_view;

/**
 * Represents a ConcreteCreator of the Factory Method pattern for creating
 * instances of the {@link RemoveFileAction} class.
 */
public class RemoveFileActionCreator implements CreatorAction {

    private String filePath;

    /**
     * Constructs a RemoveFileActionCreator with the specified file path.
     * @param filePath the file path to remove
     */
    public RemoveFileActionCreator(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Creates an instance of the {@link RemoveFileAction} class.
     *
     * @return a new instance of the RemoveFileAction class with the specified
     * file path.
     */
    @Override
    public Action create() {
        return new RemoveFileAction(filePath);
    }

}
