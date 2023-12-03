package automatehub.model_view;

public enum FileExtensionFilter {
    WAV("*.wav", "Audio Files (*.wav)"),
    TEXT("*.text", "Text Files (*.text)"),
    ALL("*.*", "All Files (*.*)");

    private final String extension;
    private final String description;

    FileExtensionFilter(String extension, String description) {
        this.extension = extension;
        this.description = description;
    }

    public String getExtension() {
        return extension;
    }

    public String getDescription() {
        return description;
    }
}
