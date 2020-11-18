package pages;

import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class Footer extends Form {

    public static final String FOOTER_ITEM_LOCATOR_PATTERN = "//footer//a[contains(text(),'%s')]";

    public Footer() {
        super(By.xpath("//footer"), "Footer");
    }

    public void openLink(String footerItemName) {
        getElementFactory().getButton(By.xpath(String.format(FOOTER_ITEM_LOCATOR_PATTERN, footerItemName)), footerItemName).click();
    }
}
