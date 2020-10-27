package pages;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.elements.ElementType;
import aquality.selenium.elements.interfaces.IElement;
import aquality.selenium.elements.interfaces.ILabel;
import aquality.selenium.forms.Form;
import org.apache.commons.lang3.RandomUtils;
import org.openqa.selenium.By;

import java.util.List;
import java.util.stream.Collectors;

public class CatalogPage extends Form {
    public static final String OPEN_CATEGORY_BUTTON_XPATH_PATTERN = "//h2[contains(text(),'%s')]//following-sibling::a";
    public static final String SUBCATEGORY_LABEL_XPATH_PATTERN = "//h2[contains(text(),\"%s\")]";
    public static final String BOOKS_COVERS = "//img[contains(@alt, 'Cover of book')]";
    public static final String ARIA_LABEL_ATTRIBUTE = "aria-label";
    private List<IElement> listOfBookNames = getElementFactory().findElements(By.xpath("//h3"), ElementType.LABEL);
    private List<IElement> listOfBookFormats =
            getElementFactory().findElements(By.xpath("//div[@role='img' and contains(@aria-label,'Cover of book:')]/div//*[name()='svg' and contains(@aria-label,'Book Medium:')]"), ElementType.LABEL);
    private ILabel lblPageName = getElementFactory().getLabel(By.xpath("//h1"), "Header");


    public CatalogPage() {
        super(By.id("cpw-content"), "Catalog");
    }

    private List<IElement> getBooksCovers() {
        AqualityServices.getConditionalWait().waitFor(webDriver ->
                getElementFactory().findElements(By.xpath(BOOKS_COVERS), ElementType.LABEL).size() > 0);
        return getElementFactory().findElements(By.xpath(BOOKS_COVERS), ElementType.LABEL)
                .stream()
                .filter(element -> element.state().isDisplayed())
                .collect(Collectors.toList());
    }

    public List<String> getBooksNames() {
        return listOfBookNames.stream().map(IElement::getText).collect(Collectors.toList());
    }

    public void openCategory(String categoryName) {
        getElementFactory().getButton(By.xpath(String.format(OPEN_CATEGORY_BUTTON_XPATH_PATTERN, categoryName)), categoryName).click();
    }

    public String getCategoryName() {
        return lblPageName.getText();
    }

    public boolean isSubcategoryPresent(String subcategoryName) {
        return getElementFactory().getButton(By.xpath(String.format(SUBCATEGORY_LABEL_XPATH_PATTERN, subcategoryName)), subcategoryName).state().isDisplayed();
    }

    public List<String> getBooksType() {
        return listOfBookFormats.stream().map(element -> element.getAttribute(ARIA_LABEL_ATTRIBUTE)).collect(Collectors.toList());
    }

    public void clickRandomVisibleBookCover() {
        List<IElement> bookCovers = getBooksCovers();
        bookCovers.get(RandomUtils.nextInt(0, bookCovers.size())).click();
    }
}
