package pages;

import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class BookPage extends Form {
    private static final String BOOK_INFO_LOC = "//*[@aria-label='Book info']";

    private final IButton borrowBook = getElementFactory().getButton(
            By.xpath("//button[./span[text()='Borrow']]"), "Borrow book");
    private final IButton downloadBtn = getElementFactory().getButton(
            By.xpath("//button[text()='Download Adobe EPUB']"), "Download book button");

    public BookPage() {
        super(By.xpath(BOOK_INFO_LOC), "Book page");
    }

    public void clickBorrowBook() {
        borrowBook.click();
    }

    public boolean isDownloadBookBtnVisible() {
        return downloadBtn.state().waitForDisplayed();
    }
}
