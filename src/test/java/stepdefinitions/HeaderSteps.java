package stepdefinitions;

import framework.configuration.Configuration;
import framework.configuration.Credentials;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import pages.HeaderPage;
import pages.SingInModal;

public class HeaderSteps {
    private final HeaderPage headerPage = new HeaderPage();
    private final SingInModal singInModal = new SingInModal();

    @When("I login to the {string}")
    public void login(String libraryName) {
        Credentials credentials = Configuration.getCredentials(libraryName);
        headerPage.openSignInModal();
        singInModal.setLogin(credentials.getBarcode());
        singInModal.setPassword(credentials.getPin());
        singInModal.applyLogin();
    }

    @Then("Login is performed successfully")
    public void checkThatLoginIsPerformedSuccessfully() {
        Assert.assertTrue(headerPage.isSignOutBtnVisible(), "Login was not performed successfully");
    }
}
