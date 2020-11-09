package aquality.selenium.configuration.driversettings;


import aquality.selenium.core.utilities.ISettingsFile;

import java.util.Arrays;
import java.util.stream.Collectors;

public class IPhoneSettings extends SafariSettings {

    public IPhoneSettings(ISettingsFile settingsFile) {
        super(settingsFile);
    }

    @Override
    String getDriverSettingsPath(String... paths) {
        String pathToDriverSettings = String.format("/driverSettings/%1$s", "iphone");
        return pathToDriverSettings.concat((String) Arrays.stream(paths).map("/"::concat).collect(Collectors.joining()));
    }
}
