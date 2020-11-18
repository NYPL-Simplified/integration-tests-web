package stepdefinitions;

import io.cucumber.java.en.When;
import pages.Footer;

public class FooterStep {
    private final Footer footer = new Footer();

    @When("I open {string} footer link")
    public void openFooterLink(String footerItemName) {
        footer.state().waitForDisplayed();
        footer.openLink(footerItemName);
    }
}
