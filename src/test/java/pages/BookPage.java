package pages;

import aquality.selenium.elements.ElementType;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ILabel;
import aquality.selenium.forms.Form;
import constants.pages.BookActionButtons;
import constants.pages.BookInfoItemConstants;
import constants.pages.BookTypeFormatMatch;
import constants.pages.ElementAttributesConstants;
import constants.pages.StringConstants;
import models.BookDetailsScreenInformationBlock;
import models.BookInfo;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;

import java.util.Arrays;
import java.util.stream.Collectors;

public class BookPage extends Form {
    private static final String BOOK_INFO_LOC = "//*[@aria-label='Book info']";
    private static final String OPEN_CATEGORY_XPATH_PATTERN =
            "//li[contains(@aria-label,'%s')]//a[contains(text(),'See More')]";
    private static final String RECOMMENDATION_BOOK_XPATH_PATTERN = "//li[contains(@aria-label,'%s')]//li";
    private static final String DESCRIPTION_INFO_XPATH_PATTERN = "//b[contains(text(),'%s')]//following-sibling::span";
    private static final String BOOK_ACTION_BUTTON = "//button[contains(text(), '%1$s')]";
    public static final String LBL_STATUS_XPATH_LOCATOR = "//div[@aria-label='Borrow and download card']//span";

    private final ILabel lblTitle = getElementFactory().getLabel(By.xpath("//h1"), "Title");
    private final ILabel lblAuthor = getElementFactory().getLabel(By.xpath("//div[@aria-label='Book info']//span"), "Author");
    private final ILabel lblFormat =
            getElementFactory().getLabel(By.xpath("//div[@aria-label='Book info']/span/*[name()='svg']"), "Format");
    private final ILabel lblDescription =
            getElementFactory().getLabel(By.xpath("//div[@aria-label='Book summary']/div"), "Description");
    private final ILabel lblStatus = getElementFactory().getLabel(By.xpath(LBL_STATUS_XPATH_LOCATOR), "Status");
    private final ILabel lblQueue =
            getElementFactory().getLabel(By.xpath(String.format("(%s)[2]", LBL_STATUS_XPATH_LOCATOR)), "Queue status");

    public BookPage() {
        super(By.xpath(BOOK_INFO_LOC), "Book page");
    }

    public void clickBookActionButton(BookActionButtons action) {
        getActionButton(action).click();
    }

    public boolean isActionBtnVisible(BookActionButtons action) {
        return getActionButton(action).state().waitForDisplayed();
    }

    public BookInfo getBookInfo() {
        BookInfo bookInfo = new BookInfo();
        bookInfo.setTitle(lblTitle.getText());
        bookInfo.setAuthor(lblAuthor.getText().replace("by ", ""));
        bookInfo.setBookType(lblFormat.getAttribute(ElementAttributesConstants.ARIA_LABEL_ATTRIBUTE));
        return bookInfo;
    }

    public String getDownloadFileName() {
        String[] splitName = lblTitle.getText().split(StringUtils.SPACE);
        return Arrays.stream(splitName)
                .map(String::toLowerCase)
                .collect(Collectors.joining(StringConstants.FILE_NAME_DELIMITER))
                + StringConstants.FILE_EXTENSION_DELIMITER
                + BookTypeFormatMatch.EPUB.getFileFormat(); // Change to the selecting format oriented to the book content when other file formats will be supported
    }

    public void openRecommendationsCategory(String categoryName) {
        getElementFactory().getButton(By.xpath(String.format(OPEN_CATEGORY_XPATH_PATTERN, categoryName)), String.format("Open %s recommendations category", categoryName)).click();
    }

    public int getCountOfBooksInRecommendationsLane(String categoryName) {
        getElementFactory().getButton(By.xpath(String.format(RECOMMENDATION_BOOK_XPATH_PATTERN, categoryName)), categoryName).getJsActions().scrollIntoView();
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

    public String getStatus() {
        return lblStatus.getText();
    }

    public String getPatronMessage() {
        return lblQueue.getText();
    }

    private String getDescriptionInfo(String key) {
        return getElementFactory().getLabel(By.xpath(String.format(DESCRIPTION_INFO_XPATH_PATTERN, key)), key).getText();
    }

    private IButton getActionButton(BookActionButtons action) {
        return getElementFactory().getButton(By.xpath(String.format(BOOK_ACTION_BUTTON, action.getAction())), action.getAction());
    }
}
