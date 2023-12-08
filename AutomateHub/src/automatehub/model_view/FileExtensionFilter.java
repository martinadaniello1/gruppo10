package automatehub.model_view;

public enum FileExtensionFilter {
    WAV("*.wav", "Audio Files (*.wav)"),
    TEXT("*.txt", "Text Files (*.txt)"),
    ALL("*.*", "All Files (*.*)"),
    DIRECTORY("*.directory", "Directory");

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