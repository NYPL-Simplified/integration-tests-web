package stepdefinitions;

import aquality.selenium.browser.AqualityServices;
import io.cucumber.java.en.When;

public class BrowserSteps {

    @When("I return to previous screen")
    public void returnToPreviousScreen() {
        AqualityServices.getBrowser().getDriver().navigate().back();
    }
}
