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
    private ILabel lblPageName = getElementFactory().getLabel(By.xpath("//h1"), "Header");
    private IComboBox cmbSortByFormat = getElementFactory().getComboBox(By.id("facet-selector-Formats"), "Sort by format");
    private ILabel lblFirstBookTitle = getElementFactory().getLabel(By.xpath("//div//h2"), "First book title");
    private ILabel lblFirstBookAuthor = getElementFactory().getLabel(By.xpath("//span[@aria-label='Authors']"), "First book author");
    private ILabel lblFirstBookFormat = getElementFactory().getLabel(By.xpath("//div//*[name()='svg' and contains(@aria-label,'Book Medium:')]"), "First book format");
    private IButton btnViewFirstBookDetails = getElementFactory().getButton(By.xpath("//div//a[contains(text(),'Read more')]"), "View first book details");
    private IButton btnViewMore = getElementFactory().getButton(By.xpath("//button[contains(text(),'View more')]"), "View more");

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
        return getListOfTextValues(getListOfElements("//li//h2"));
    }

    public List<String> getAuthors() {
        return getListOfTextValues(getListOfElements("//span[@aria-label='Authors']"));
    }

    private List<String> getListOfTextValues(List<IElement> list) {
        return list.stream().map(IElement::getText).collect(Collectors.toList());
    }

    private List<IElement> getListOfElements(String s) {
        return getElementFactory().findElements(By.xpath(s), ElementType.LABEL);
    }

    public void cancelReservationForBook(BookInfo bookInfo) {
        String title = bookInfo.getTitle();
        getElementFactory().getButton(By.xpath(String.format("//h2[./a[contains(@aria-label,'%1$s')]]/parent::div/following-sibling::button[contains(text(),'%2$s')]", title, BookActionButtons.CANCEL_RESERVATION.getAction())), title).click();
    }

    public boolean isBookPresent(BookInfo bookInfo) {
        String title = bookInfo.getTitle();
        return getElementFactory().getButton(By.xpath(String.format("//h2[./a[contains(@aria-label,'%1$s')]]", title)), title).state().isDisplayed();
    }

    public void openBookWithGivenActionButton(BookActionButtons bookActionButtons) {
        IButton btnOpenBookWithGivenButton = getElementFactory().getButton(By.xpath(String.format("//div[./button[contains(text(),'%s')]]//following-sibling::div/a", bookActionButtons.getAction())), "View book details");
        if (!btnOpenBookWithGivenButton.state().isDisplayed()) {
            AqualityServices.getConditionalWait().waitFor(() -> {
                btnViewMore.state().waitForClickable();
                btnViewMore.click();
                return btnOpenBookWithGivenButton.state().isDisplayed();
            }, Duration.ofSeconds(BookConstants.BOOK_SEARCH.getTimeout()));
        }
        btnOpenBookWithGivenButton.click();
    }
}
