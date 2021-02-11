package models;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class BookLabelsVisibility {
    private boolean readyToReadOnSimplyEMessage;
    private boolean downloadAdobeACSM;
    private boolean readyToListenOnSimplyEMessage;
    private boolean downloadEPUB;
}
