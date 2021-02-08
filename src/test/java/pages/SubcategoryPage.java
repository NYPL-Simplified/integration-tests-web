package pages;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.elements.ElementType;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.IComboBox;
import aquality.selenium.elements.interfaces.IElement;
import aquality.selenium.elements.interfaces.ILabel;
import aquality.selenium.forms.Form;
import constants.pages.BookActionButtons;
import constants.pages.BookConstants;
import constants.pages.ElementAttributesConstants;
import models.BookInfo;
import org.openqa.selenium.By;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

public class SubcategoryPage extends Form {
    private static final String BOOK_WITH_GIVEN_BUTTON_LOCATOR_XPATH_PATTERN =
            "//div[./button[contains(text(),'%s')]]//following-sibling::div/a";
    private static final String BOOK_XPATH_LOCATOR_PATTERN = "//h2[./a[contains(@aria-label,'%1$s')]]";
    private static final String FOLLOWING_BUTTON_LOCATOR_XPATH_PATTERN =
            "/parent::div/following-sibling::button[contains(text(),'%2$s')]";
    private static final String BOOK_TITLES_LOCATOR_XPATH = "//li//h2";
    private static final String AUTHORS_XPATH_LOCATOR = "//span[@aria-label='Authors']";
    private static final String BOOK_NAME_LOCATOR_PATTERN = "//h2[./a[contains(@aria-label,\"%1$s\")]]";

    private ILabel lblPageName = getElementFactory().getLabel(By.xpath("//h1"), "Header");
    private IComboBox cmbSortByFormat = getElementFactory().getComboBox(By.id("facet-selector-Formats"), "Sort by format");
    private ILabel lblFirstBookTitle = getElementFactory().getLabel(By.xpath("//div//h2"), "First book title");
    private ILabel lblFirstBookAuthor = getElementFactory().getLabel(By.xpath("//span[@aria-label='Authors']"), "First book author");
    private ILabel lblFirstBookFormat =
            getElementFactory().getLabel(By.xpath("//div//*[name()='svg' and contains(@aria-label,'Book Medium:')]"), "First book format");
    private IButton btnViewFirstBookDetails =
            getElementFactory().getButton(By.xpath("//div//a[contains(text(),'Read more')]"), "View first book details");
    private IButton btnViewMore = getElementFactory().getButton(By.xpath("//button[contains(text(),'View more')]"), "View more");
    private IButton btnDownloadName =
            getElementFactory().getButton(By.xpath("//li[contains(@aria-label,'Book: ') and .//button[contains(text(), 'Download')]]//a[not(contains(@aria-label, 'View'))]"), "Download name");
    private IButton btnDownloadAuthor =
            getElementFactory().getButton(By.xpath("//li[contains(@aria-label,'Book: ') and .//button[contains(text(), 'Download')]]//span[@aria-label='Authors']"), "Download author");
    private IButton btnDownload = getElementFactory().getButton(By.xpath("//button[contains(text(), 'Download')]"), "Download");

    public SubcategoryPage() {
        super(By.id("facet-selector-Sort by"), "Subcategory");
    }

    public String getSubcategoryName() {
        return lblPageName.getText();
    }

    public void sortByFormat(String format) {
        cmbSortByFormat.selectByContainingText(format);
    }

    public BookInfo openFirstBook() {
        BookInfo bookInfo = new BookInfo();
        bookInfo.setAuthor(lblFirstBookAuthor.getText());
        bookInfo.setBookType(lblFirstBookFormat.getAttribute(ElementAttributesConstants.ARIA_LABEL_ATTRIBUTE));
        bookInfo.setTitle(lblFirstBookTitle.getText());
        btnViewFirstBookDetails.click();
        return bookInfo;
    }

    public List<String> getBookTitles() {
        return getListOfTextValues(getListOfElements(BOOK_TITLES_LOCATOR_XPATH));
    }

    public List<String> getAuthors() {
        return getListOfTextValues(getListOfElements(AUTHORS_XPATH_LOCATOR));
    }

    public void cancelReservationForBook(BookInfo bookInfo) {
        String title = bookInfo.getTitle();
        getElementFactory().getButton(By.xpath(String.format(BOOK_XPATH_LOCATOR_PATTERN + FOLLOWING_BUTTON_LOCATOR_XPATH_PATTERN, title, BookActionButtons.CANCEL_RESERVATION.getAction())), title).click();
    }

    public boolean isBookPresent(BookInfo bookInfo) {
        String title = bookInfo.getTitle();
        return getElementFactory().getButton(By.xpath(String.format(BOOK_XPATH_LOCATOR_PATTERN, title)), title).state().isDisplayed();
    }

    public void openBookWithGivenActionButton(BookActionButtons bookActionButtons) {
        IButton btnOpenBookWithGivenButton =
                getElementFactory().getButton(By.xpath(String.format(BOOK_WITH_GIVEN_BUTTON_LOCATOR_XPATH_PATTERN, bookActionButtons.getAction())), "View book details");
        if (!btnOpenBookWithGivenButton.state().isDisplayed()) {
            AqualityServices.getConditionalWait().waitFor(() -> {
                btnViewMore.state().waitForDisplayed();
                btnViewMore.click();
                return btnOpenBookWithGivenButton.state().isDisplayed();
            }, Duration.ofSeconds(BookConstants.BOOK_SEARCH.getTimeout()));
        }
        btnOpenBookWithGivenButton.click();
    }

    public BookInfo downloadBook() {
        state().waitForDisplayed();
        BookInfo bookInfo = new BookInfo();
        bookInfo.setTitle(btnDownloadName.getAttribute(ElementAttributesConstants.ARIA_LABEL_ATTRIBUTE));
        bookInfo.setAuthor(btnDownloadAuthor.getText());
        btnDownload.click();
        return bookInfo;
    }

    public void openBook(BookInfo bookInfo) {
        getBookNameButton(bookInfo).click();
    }

    public void openBook(String bookName) {
        getBookNameButton(bookName).click();
    }

    private List<String> getListOfTextValues(List<IElement> list) {
        return list.stream().map(IElement::getText).collect(Collectors.toList());
    }

    private List<IElement> getListOfElements(String s) {
        return getElementFactory().findElements(By.xpath(s), ElementType.LABEL);
    }

    private IButton getBookNameButton(BookInfo bookInfo) {
        return getBookNameButton(bookInfo.getTitle());
    }

    private IButton getBookNameButton(String bookName) {
        return getElementFactory().getButton(By.xpath(String.format(BOOK_NAME_LOCATOR_PATTERN, bookName)), bookName);
    }
}
