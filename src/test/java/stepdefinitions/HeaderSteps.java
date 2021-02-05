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
import pages.SubcategoryPage;

public class HeaderSteps {
    private final Header header = new Header();
    private final SingInModal singInModal = new SingInModal();
    private final SearchResultPage searchResultPage = new SearchResultPage();
    private final SubcategoryPage subcategoryPage = new SubcategoryPage();

    @When("I login to the {string}")
    public void login(String libraryName) {
        header.openSignInModal();
        loginWithCredentials(libraryName);
    }

    @When("I login using {string} credentials")
    public void loginUsingCredentials(String libraryName) {
        loginWithCredentials(libraryName);
    }

    @Then("Login is performed successfully")
    public void checkThatLoginIsPerformedSuccessfully() {
        Assert.assertTrue(header.isSignOutButtonVisible(), "Login was not performed successfully");
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

    @When("I open main library page")
    public void openMainLibraryPage() {
        subcategoryPage.state().waitForDisplayed();
        header.openMainPage();
    }

    private void loginWithCredentials(String libraryName) {
        Credentials credentials = Configuration.getCredentials(libraryName);
        singInModal.setLogin(credentials.getBarcode());
        singInModal.setPassword(credentials.getPin());
        singInModal.applyLogin();
    }
}
