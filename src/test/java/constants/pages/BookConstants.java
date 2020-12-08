package constants.pages;

public enum BookConstants {
    BOOK_DOWNLOAD(45),
    BOOK_SEARCH(120);

    private int timeoutInSeconds;

    BookConstants(int timeoutInSeconds) {

        this.timeoutInSeconds = timeoutInSeconds;
    }

    public int getTimeout() {
        return timeoutInSeconds;
    }
}
