package models;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Map;

@Data
@Accessors(chain = true)
public class BookLabelsVisibility {
    private boolean readyToReadOnSimplyEMessage;
    private boolean downloadAdobeACSM;
    private boolean readyToListenOnSimplyEMessage;
    private boolean downloadEPUB;

    public static BookLabelsVisibility createBookLabelsVisibilityModel(Map<String, String> entry) {
        BookLabelsVisibility visibility = new BookLabelsVisibility();
        visibility.setDownloadAdobeACSM(Boolean.parseBoolean(entry.get("downloadAdobeACSM")));
        visibility.setDownloadEPUB(Boolean.parseBoolean(entry.get("downloadEPUB")));
        visibility.setReadyToListenOnSimplyEMessage(Boolean.parseBoolean(entry.get("readyToListenOnSimplyEMessage")));
        visibility.setReadyToReadOnSimplyEMessage(Boolean.parseBoolean(entry.get("readyToReadOnSimplyEMessage")));
        return visibility;
    }
}
