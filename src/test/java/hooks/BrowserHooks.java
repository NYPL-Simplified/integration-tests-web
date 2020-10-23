package hooks;

import aquality.selenium.browser.AqualityServices;
import framework.configuration.Configuration;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class BrowserHooks {

    @After(order = 1)
    public void closeBrowser() {
        if (AqualityServices.isBrowserStarted()) {
            AqualityServices.getBrowser().quit();
        }
    }

    @Before(order = 1)
    public void startBrowser() {
        AqualityServices.getBrowser().maximize();
        AqualityServices.getBrowser().goTo(Configuration.getStartUrl());
    }
}
