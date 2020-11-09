package pages;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.elements.ElementType;
import aquality.selenium.elements.interfaces.IElement;
import aquality.selenium.elements.interfaces.ILabel;
import aquality.selenium.forms.Form;
import constants.pages.ElementAttributesConstants;
import org.apache.commons.lang3.RandomUtils;
import org.openqa.selenium.By;

import java.util.List;
import java.util.stream.Collectors;

public class CatalogPage extends Form {
    private static final String OPEN_CATEGORY_BUTTON_XPATH_PATTERN = "//h2[contains(text(),'%s')]//following-sibling::a";
    private static final String SUBCATEGORY_LABEL_XPATH_PATTERN = "//h2[contains(text(),\"%s\")]";
    private static final String BOOKS_COVERS = "//img[contains(@alt, 'Cover of book')]";
    private static final String BOOK_TITLES_LOCATOR = "//h3";
    public static final String BOOK_TYPES_LOCATOR =
            "//div[@role='img' and contains(@aria-label,'Cover of book:')]/div//*[name()='svg' and contains(@aria-label,'Book Medium:')]";
    public static final String BOOKS_IN_FIRST_LANE_LOCATOR = "(//ul[@data-testid])[1]//h3";

    private ILabel lblPageName = getElementFactory().getLabel(By.xpath("//h1"), "Header");

    public CatalogPage() {
        super(By.id("cpw-content"), "Catalog");
    }

    private List<IElement> getBooksCovers() {
        AqualityServices.getConditionalWait().waitFor(webDriver ->
                getListOfElements(BOOKS_COVERS).size() > 0);
        return getListOfElements(BOOKS_COVERS)
                .stream()
                .filter(element -> element.state().isDisplayed())
                .collect(Collectors.toList());
    }

    public List<String> getBooksTitles() {
        return getListOfElements(BOOK_TITLES_LOCATOR).stream().map(IElement::getText).collect(Collectors.toList());
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
        AqualityServices.getConditionalWait().waitFor(() -> getListOfElements(CatalogPage.BOOK_TYPES_LOCATOR).size() > 0);
        return getListOfElements(CatalogPage.BOOK_TYPES_LOCATOR).stream().map(element -> element.getAttribute(ElementAttributesConstants.ARIA_LABEL_ATTRIBUTE)).collect(Collectors.toList());
    }

    public void clickRandomVisibleBookCover() {
        List<IElement> bookCovers = getBooksCovers();
        bookCovers.get(RandomUtils.nextInt(0, bookCovers.size())).click();
    }

    public List<String> getListOfAllBooksNamesInFirstLane() {
        return getValuesFromListOfLabels(getListOfElements(BOOKS_IN_FIRST_LANE_LOCATOR));
    }

    private List<String> getValuesFromListOfLabels(List<IElement> elements) {
        return elements.stream().map(IElement::getText).collect(Collectors.toList());
    }

    private List<IElement> getListOfElements(String locator) {
        return getElementFactory().findElements(By.xpath(locator), ElementType.LABEL);
    }
}
