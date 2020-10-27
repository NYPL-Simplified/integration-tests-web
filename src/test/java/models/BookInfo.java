package models;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class BookInfo {
    public BookInfo() {
    }

    private String title;
    private String author;
    private String bookType;
}