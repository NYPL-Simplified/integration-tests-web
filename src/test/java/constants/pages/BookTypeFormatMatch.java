package constants.pages;

public enum BookTypeFormatMatch {
    PDF("eBook", "pdf"),
    EPUB("eBook", "epub");

    private final String type;
    private final String fileFormat;

    BookTypeFormatMatch(String type, String fileFormat) {
        this.type = type;
        this.fileFormat = fileFormat;
    }

    public String getType() {
        return type;
    }

    public String getFileFormat() {
        return fileFormat;
    }
}
