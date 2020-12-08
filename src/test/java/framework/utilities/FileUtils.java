package framework.utilities;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.core.logging.Logger;
import constants.pages.BookConstants;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriverException;

import java.time.Duration;

public final class FileUtils {
    private FileUtils() {
    }

    public static boolean isFileContainingNameDownloaded(String fileName) {
        return AqualityServices.getConfiguration().getBrowserProfile().isRemote() ?
                isFileContainingNameDownloadedRemote(fileName) : isFileContainingNameDownloadedLocal(fileName);
    }

    private static boolean isFileContainingNameDownloadedRemote(String fileName) {
        return AqualityServices.getConditionalWait().waitFor(driver ->
                (boolean) AqualityServices.getBrowser().executeScript(String.format("browserstack_executor: {\"action\": \"fileExists\", \"arguments\": {\"fileName\": \"%1$s\"}}", fileName)), Duration.ofSeconds(BookConstants.BOOK_DOWNLOAD.getTimeout()));
    }

    private static boolean isFileContainingNameDownloadedLocal(String fileName) {
        String downloadPath = getTargetFilePath(fileName);
        try {
            AqualityServices.getBrowser().goTo(downloadPath);
            return AqualityServices.getElementFactory()
                    .getLabel(By.xpath(String.format("//a[contains(text(), '%1$s')]", fileName)), "Book to download")
                    .state()
                    .waitForDisplayed();
        } catch (WebDriverException e) {
            Logger.getInstance().warn(e.getMessage());
            return false;
        }
    }

    public static String getTargetFilePath(String fileName) {
        String downloadDirectory = AqualityServices.getBrowser().getDownloadDirectory();

        // below is workaround for case when local FS is different from remote (e.g. local machine runs on Windows but remote runs on Linux)
        if (downloadDirectory.contains("/") && !downloadDirectory.endsWith("/")) {
            downloadDirectory = downloadDirectory.concat("/");
        }
        if (downloadDirectory.contains("\\") && !downloadDirectory.endsWith("\\")) {
            downloadDirectory = downloadDirectory.concat("\\");
        }
        return downloadDirectory.concat(fileName);
    }
}
