package pages;

import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ILabel;
import aquality.selenium.forms.Form;
import constants.pages.ElementAttributesConstants;
import models.BookInfo;
import org.openqa.selenium.By;

public class BookPage extends Form {
    private static final String BOOK_INFO_LOC = "//*[@aria-label='Book info']";

    private final IButton btnBorrowBook = getElementFactory().getButton(
            By.xpath("//button[./span[text()='Borrow']]"), "Borrow book");
    private final IButton downloadBtn = getElementFactory().getButton(
            By.xpath("//button[text()='Download Adobe EPUB']"), "Download book button");
    private final ILabel lblTitle = getElementFactory().getLabel(By.xpath("//h1"), "Title");
    private final ILabel lblAuthor = getElementFactory().getLabel(By.xpath("//div[@aria-label='Book info']//span"), "Author");
    private final ILabel lblFormat =
            getElementFactory().getLabel(By.xpath("//div[@aria-label='Book info']/span/*[name()='svg']"), "Format");

    public BookPage() {
        super(By.xpath(BOOK_INFO_LOC), "Book page");
    }

    public void clickBorrowBook() {
        btnBorrowBook.click();
    }

    public boolean isDownloadBookBtnVisible() {
        return downloadBtn.state().waitForDisplayed();
    }

    public BookInfo getBookInfo() {
        BookInfo bookInfo = new BookInfo();
        bookInfo.setTitle(lblTitle.getText());
        bookInfo.setAuthor(lblAuthor.getText().replace("by ", ""));
        bookInfo.setBookType(lblFormat.getAttribute(ElementAttributesConstants.ARIA_LABEL_ATTRIBUTE));
        return bookInfo;
    }
}
