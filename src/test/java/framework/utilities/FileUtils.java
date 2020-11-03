package framework.utilities;

import aquality.selenium.browser.AqualityServices;

public final class FileUtils {
    private FileUtils() {
    }

    public static boolean isFileContainingNameDownloaded(String fileName) {
        return AqualityServices.getConditionalWait().waitFor(driver -> (boolean) AqualityServices.getBrowser()
                .executeScript(String.format("browserstack_executor: "
                        + "{\"action\": \"fileExists\", \"argument"
                        + "s\": {\"fileName\": \"%1$s\"}}", fileName)),
                "Checking that file downloaded successfully to the download dir");
    }
}
