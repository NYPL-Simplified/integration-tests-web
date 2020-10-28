package pages;

import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.IComboBox;
import aquality.selenium.elements.interfaces.ILabel;
import aquality.selenium.forms.Form;
import constants.pages.ElementAttributesConstants;
import models.BookInfo;
import org.openqa.selenium.By;

public class SubcategoryPage extends Form {
    private ILabel lblPageName = getElementFactory().getLabel(By.xpath("//h1"), "Header");
    private IComboBox cmbSortByFormat = getElementFactory().getComboBox(By.id("facet-selector-Formats"), "Sort by format");
    private ILabel lblFirstBookTitle = getElementFactory().getLabel(By.xpath("//div[@class='card__content']//h2"), "First book title");
    private ILabel lblFirstBookAuthor = getElementFactory().getLabel(By.xpath("//div[@class='card__content']//span[.//*[name()='svg']]//preceding-sibling::span[1]"), "First book author");
    private ILabel lblFirstBookFormat = getElementFactory().getLabel(By.xpath("//div[@class='card__content']//*[name()='svg' and contains(@aria-label,'Book Medium:')]"), "First book format");
    private IButton btnViewFirstBookDetails = getElementFactory().getButton(By.xpath("//div[@class='card__ctas']//a"), "View first book details");

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
}
