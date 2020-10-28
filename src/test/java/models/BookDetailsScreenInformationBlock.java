package models;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class BookDetailsScreenInformationBlock {
    private String published;
    private String publisher;
    private String categories;
}
