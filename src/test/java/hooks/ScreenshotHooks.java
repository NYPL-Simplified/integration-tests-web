package hooks;

import aquality.selenium.browser.AqualityServices;
import io.cucumber.java.After;
import io.cucumber.java.Scenario;

public class ScreenshotHooks {
    @After(order = 2)
    public void takeScreenshot(Scenario scenario) {
        scenario.attach(AqualityServices.getBrowser().getScreenshot(), "image/png", "screenshot.png");
    }
}
