package automatehub.model_view.action.creator;

import automatehub.model_view.action.Action;
import automatehub.model_view.action.MoveFileAction;

/**
 * Represents a ConcreteCreator of the Factory Method pattern for creating
 * instances of the {@link MoveFileAction} class.
 */
public class MoveFileActionCreator implements CreatorAction {

    private String startingPath;
    private String destinationPath;

    /**
     * Constructs a RemoveFileActionCreator with the specified file path and the
     * specified directory.
     *
     * @param startingPath the file to move
     * @param destinationPath the directory in which to move the file
     */
    public MoveFileActionCreator(String startingPath, String destinationPath) {
        this.startingPath = startingPath;
        this.destinationPath = destinationPath;
    }

      /**
     * Creates an instance of the {@link MoveFileAction} class.
     *
     * @return a new instance of the MoveFileAction class with the specified
     * file path and the specified directory.
     */
    @Override
    public Action create() {
        return new MoveFileAction(startingPath, destinationPath);
    }

}
