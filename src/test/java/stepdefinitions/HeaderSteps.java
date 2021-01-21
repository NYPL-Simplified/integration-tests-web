package stepdefinitions;

import framework.configuration.Configuration;
import framework.configuration.Credentials;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import pages.Header;
import pages.SearchResultPage;
import pages.SingInModal;

public class HeaderSteps {
    private final Header header = new Header();
    private final SingInModal singInModal = new SingInModal();
    private final SearchResultPage searchResultPage = new SearchResultPage();

    @When("I login to the {string}")
    public void login(String libraryName) {
        Credentials credentials = Configuration.getCredentials(libraryName);
        header.openSignInModal();
        singInModal.setLogin(credentials.getBarcode());
        singInModal.setPassword(credentials.getPin());
        singInModal.applyLogin();
    }

    @Then("Login is performed successfully")
    public void checkThatLoginIsPerformedSuccessfully() {
        Assert.assertTrue(header.isSignOutBtnVisible(), "Login was not performed successfully");
    }

    @And("I open {string} item from breadcrumbs")
    public void openItemFromBreadcrumbs(String breadcrumbItemName) {
        header.openBreadcrumb(breadcrumbItemName);
    }

    @When("I search for {string} book")
    public void searchForBook(String searchItem) {
        header.searchFor(searchItem);
        searchResultPage.state().waitForDisplayed();
    }

    @When("I open My books")
    public void openMyBooks() {
        header.openMyBooks();
    }
}
