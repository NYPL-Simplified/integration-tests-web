package stepdefinitions;

import aquality.selenium.browser.AqualityServices;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;

import java.util.ArrayList;

public class BrowserSteps {

    @When("I return to previous screen")
    public void returnToPreviousScreen() {
        getDriver().navigate().back();
    }

    @Then("New tab with link containing {string} is opened")
    public void checkNewTabWithLinkContainingIsOpened(String partOfUrl) {
        String firstTab = getDriver().getWindowHandle();
        ArrayList<String> currentWindows = new ArrayList<>(getDriver().getWindowHandles());
        for (String window : currentWindows) {
            if (!window.equals(firstTab)) {
                getDriver().switchTo().window(window);
            }
        }
        Assert.assertTrue(AqualityServices.getConditionalWait().waitFor(() -> getDriver().getCurrentUrl().contains(partOfUrl)),
                "Current url does not contain '" + partOfUrl + "'. Current url - " + getDriver().getCurrentUrl());
        getDriver().close();
        getDriver().switchTo().window(firstTab);
    }

    private RemoteWebDriver getDriver() {
        return AqualityServices.getBrowser().getDriver();
    }
}
