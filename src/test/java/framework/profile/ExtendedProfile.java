package framework.profile;

import aquality.selenium.browser.BrowserName;
import aquality.selenium.configuration.BrowserProfile;
import aquality.selenium.configuration.driversettings.AndroidSettings;
import aquality.selenium.configuration.driversettings.IDriverSettings;
import aquality.selenium.configuration.driversettings.IPhoneSettings;
import aquality.selenium.core.utilities.ISettingsFile;
import com.google.inject.Inject;

public class ExtendedProfile extends BrowserProfile {
    private ISettingsFile settingsFile;

    @Override
    public BrowserName getBrowserName() {
        String browserName = String.valueOf(this.settingsFile.getValue("/browserName")).toUpperCase();
        switch (browserName) {
            case "IPHONE":
            case "ANDROID":
                return BrowserName.SAFARI;
            default:
                return super.getBrowserName();
        }
    }

    @Inject
    public ExtendedProfile(ISettingsFile settingsFile) {
        super(settingsFile);
        this.settingsFile = settingsFile;
    }

    @Override
    public IDriverSettings getDriverSettings() {
        switch (String.valueOf(this.settingsFile.getValue("/browserName")).toUpperCase()) {
            case "IPHONE":
                return new IPhoneSettings(settingsFile);
            case "ANDROID":
                return new AndroidSettings(settingsFile);
            default:
                return super.getDriverSettings();
        }
    }
}
