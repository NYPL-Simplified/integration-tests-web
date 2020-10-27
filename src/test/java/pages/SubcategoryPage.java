package pages;

import aquality.selenium.elements.interfaces.ILabel;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class SubcategoryPage extends Form {
    private ILabel lblPageName = getElementFactory().getLabel(By.xpath("//h1"), "Header");

    public SubcategoryPage() {
        super(By.id("facet-selector-Sort by"), "Subcategory");
    }

    public String getSubcategoryName() {
        return lblPageName.getText();
    }
}
