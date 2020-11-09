package framework.profile;

import aquality.selenium.browser.Browser;
import aquality.selenium.browser.BrowserModule;
import aquality.selenium.configuration.IBrowserProfile;
import com.google.inject.Provider;

public class ExtendedBrowserModule extends BrowserModule {
    public ExtendedBrowserModule(Provider<Browser> applicationProvider) {
        super(applicationProvider);
    }

    @Override
    public Class<? extends IBrowserProfile> getBrowserProfileImplementation() {
        return ExtendedProfile.class;
    }
}
