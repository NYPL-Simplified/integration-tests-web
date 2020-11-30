package hooks;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.browser.BrowserName;
import framework.configuration.Configuration;
import framework.profile.ExtendedBrowserModule;
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
        AqualityServices.initInjector(new ExtendedBrowserModule(AqualityServices::getBrowser));
        AqualityServices.getBrowser().goTo(Configuration.getStartUrl());
        if (AqualityServices.getConfiguration().getBrowserProfile().getBrowserName() != BrowserName.SAFARI) {
            AqualityServices.getBrowser().maximize();
        }
        AqualityServices.getLogger().info("Session id - " + AqualityServices.getBrowser().getDriver().getSessionId().toString());
    }
}
