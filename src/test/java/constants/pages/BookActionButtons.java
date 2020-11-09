package constants.pages;

public enum BookActionButtons {
    BORROW("Borrow"),
    DOWNLOAD_EPUB("Download EPUB"),
    DOWNLOAD_EPUB_ADOBE("Download Adobe EPUB"),
    RETURN("Return");

    private final String action;

    BookActionButtons(String action) {
        this.action = action;
    }

    public String getAction() {
        return this.action;
    }
}
