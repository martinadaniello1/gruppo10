package automatehub.model_view;

public class FileSizeTriggerCreator implements CreatorTrigger {

    private String filePath;
    private Long specifiedSize;

    public FileSizeTriggerCreator(String filePath, Long specifiedSize) {
        this.filePath = filePath;
        this.specifiedSize = specifiedSize;
    }

    @Override
    public Trigger create() {
        return new FileSizeTrigger(filePath, specifiedSize);
    }

}
