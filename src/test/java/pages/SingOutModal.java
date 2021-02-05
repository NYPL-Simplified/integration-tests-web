package pages;

import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class SingOutModal extends Form {
    private final IButton btnSignOut =
            getElementFactory().getButton(By.xpath("//button[text()='Sign out' and @aria-label]"), "Sign out");

    public SingOutModal() {
        super(By.xpath("//*[@aria-label='Sign Out']"), "Sign out modal");
    }

    public void clickSignOut() {
        btnSignOut.click();
    }
}
