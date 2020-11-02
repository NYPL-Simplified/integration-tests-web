package pages;

import aquality.selenium.elements.ElementType;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ILabel;
import aquality.selenium.forms.Form;
import constants.pages.BookInfoItemConstants;
import constants.pages.ElementAttributesConstants;
import models.BookDetailsScreenInformationBlock;
import models.BookInfo;
import org.openqa.selenium.By;

public class BookPage extends Form {
    private static final String BOOK_INFO_LOC = "//*[@aria-label='Book info']";
    private static final String OPEN_CATEGORY_XPATH_PATTERN =
            "//li[contains(@aria-label,'%s')]//a[contains(text(),'See More')]";
    private static final String RECOMMENDATION_BOOK_XPATH_PATTERN = "//li[contains(@aria-label,'%s')]//li";
    private static final String DESCRIPTION_INFO_XPATH_PATTERN = "//b[contains(text(),'%s')]//following-sibling::span";

    private final IButton btnBorrowBook =
            getElementFactory().getButton(By.xpath("//button[contains(text(),'Borrow')]"), "Borrow book");
    private final IButton downloadBtn = getElementFactory().getButton(
            By.xpath("//button[text()='Download Adobe EPUB']"), "Download book button");
    private final ILabel lblTitle = getElementFactory().getLabel(By.xpath("//h1"), "Title");
    private final ILabel lblAuthor = getElementFactory().getLabel(By.xpath("//div[@aria-label='Book info']//span"), "Author");
    private final ILabel lblFormat =
            getElementFactory().getLabel(By.xpath("//div[@aria-label='Book info']/span/*[name()='svg']"), "Format");
    private final ILabel lblDescription =
            getElementFactory().getLabel(By.xpath("//div[@aria-label='Book summary']/div"), "Description");

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

    public void openRecommendationsCategory(String categoryName) {
        getElementFactory().getButton(By.xpath(String.format(OPEN_CATEGORY_XPATH_PATTERN, categoryName)), String.format("Open %s recommendations category", categoryName)).click();
    }

    public int getCountOfBooksInRecommendationsLane(String categoryName) {
        return getElementFactory().findElements(By.xpath(String.format(RECOMMENDATION_BOOK_XPATH_PATTERN, categoryName)), ElementType.LABEL).size();
    }

    public String getDescription() {
        return lblDescription.getText();
    }

    public BookDetailsScreenInformationBlock getBookDescriptionInfo() {
        BookDetailsScreenInformationBlock bookDetailsScreenInformationBlock = new BookDetailsScreenInformationBlock();
        bookDetailsScreenInformationBlock.setPublisher(getDescriptionInfo(BookInfoItemConstants.PUBLISHER_INFO_KEY));
        bookDetailsScreenInformationBlock.setCategories(getDescriptionInfo(BookInfoItemConstants.CATEGORIES_INFO_KEY));
        bookDetailsScreenInformationBlock.setPublished(getDescriptionInfo(BookInfoItemConstants.PUBLISHED_INFO_KEY));
        return bookDetailsScreenInformationBlock;
    }

    private String getDescriptionInfo(String key) {
        return getElementFactory().getLabel(By.xpath(String.format(DESCRIPTION_INFO_XPATH_PATTERN, key)), key).getText();
    }
}
