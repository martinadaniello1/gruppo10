package automatehub.model_view.trigger.creator;

import automatehub.model_view.trigger.*;

/**
 * Represents a ConcreteCreator of the Factory Method pattern for creating
 * instances of the {@link FileSizeTrigger} class.
 */
public class FileSizeTriggerCreator implements CreatorTrigger {

    private String filePath;
    private Long specifiedSize;

    /**
     * Constructs a FileSizeTriggerCreator with the specified file path and
     * specified Long value for the size.
     *
     * @param filePath the file path to check
     * @param specifiedSize the size to compare
     */
    public FileSizeTriggerCreator(String filePath, Long specifiedSize) {
        this.filePath = filePath;
        this.specifiedSize = specifiedSize;
    }

    /**
     * Creates an instance of the {@link FileSizeTrigger} class.
     *
     * @return a new instance of the FileSizeTrigger class with the specified
     * file path and specified size.
     */
    @Override
    public Trigger create() {
        return new FileSizeTrigger(filePath, specifiedSize);
    }

}
