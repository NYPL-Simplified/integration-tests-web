package pages;

import aquality.selenium.elements.interfaces.IComboBox;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class SearchResultPage extends Form {
    public static final String BUTTON_READ_MORE_FOR_BOOK_WITH_GIVEN_STATUS_XPATH_PATTERN =
            "//div[..//span[contains(text(),'%s')]]/a[contains(text(),'Read more')]";
    private IComboBox cmbSelectType = getElementFactory().getComboBox(By.id("facet-selector-Formats"), "Type");

    public SearchResultPage() {
        super(By.xpath("//h1[contains(text(), 'Search')]"), "Search result");
    }

    public void selectBookType(String bookType) {
        cmbSelectType.selectByContainingText(bookType);
    }

    public void openBookWithStatus(String bookStatus) {
        getElementFactory().getButton(By.xpath(String.format(BUTTON_READ_MORE_FOR_BOOK_WITH_GIVEN_STATUS_XPATH_PATTERN, bookStatus)), "Read more").click();
    }
}
