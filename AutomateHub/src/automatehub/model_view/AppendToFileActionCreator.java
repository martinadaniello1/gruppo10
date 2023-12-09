package automatehub.model_view;

/**
 * Represents a ConcreteCreator of the Factory Method pattern for creating
 * instances of the {@link AppendToFileAction} class.
 */
public class AppendToFileActionCreator implements CreatorAction {

    private String stringToAppend;
    private String filePath;

    /**
     * Constructs an AppendToFileActionCreator with the specified string to
     * append and file path.
     *
     * @param stringToAppend the string to be appended to the file.
     * @param filePath the path of the text file where the string will be
     * appended.
     */
    public AppendToFileActionCreator(String stringToAppend, String filePath) {
        this.stringToAppend = stringToAppend;
        this.filePath = filePath;
    }

    /**
     * Creates an instance of the {@link AppendToFileAction} class.
     *
     * @return a new instance of the AppendToFileAction class with the specified
     * string to append and file path.
     */
    @Override
    public Action create() {
        return new AppendToFileAction(stringToAppend, filePath);
    }
}
