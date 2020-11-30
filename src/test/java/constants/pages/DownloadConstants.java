package constants.pages;

public enum DownloadConstants {
    BOOK_DOWNLOAD(45);

    private int timeoutInSeconds;

    DownloadConstants(int timeoutInSeconds) {

        this.timeoutInSeconds = timeoutInSeconds;
    }

    public int getTimeout() {
        return timeoutInSeconds;
    }
}
