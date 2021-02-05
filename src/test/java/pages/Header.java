package pages;

import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ITextBox;
import aquality.selenium.forms.Form;
import constants.pages.HeaderPageConstants;
import org.openqa.selenium.By;

public class Header extends Form {
    private static final String LOGIN_LOGOUT_BUTTON_LOC = "//*[text()='%1$s']";
    private static final String BREADCRUMB_XPATH_PATTERN = "//a[contains(text(),'%s')]";

    private final IButton btnSignIn =
            getElementFactory().getButton(By.xpath(String.format(LOGIN_LOGOUT_BUTTON_LOC, HeaderPageConstants.SIGN_IN)), HeaderPageConstants.SIGN_IN);
    private final IButton btnSignOut =
            getElementFactory().getButton(By.xpath(String.format(LOGIN_LOGOUT_BUTTON_LOC, HeaderPageConstants.SIGN_OUT)), HeaderPageConstants.SIGN_OUT);
    private final IButton btnSearch = getElementFactory().getButton(By.xpath("//form[@role='search']/button"), "Search");
    private final IButton btnLogo = getElementFactory().getButton(By.xpath("//a/img"), "Main page");
    private final IButton btnMyBooks =
            getElementFactory().getButton(By.xpath(String.format(BREADCRUMB_XPATH_PATTERN, "My Books")), "My Books");
    private final ITextBox txbSearchItem = getElementFactory().getTextBox(By.id("search-bar"), "Search bar");

    public Header() {
        super(By.xpath("//header"), "Header");
    }

    public void openSignInModal() {
        btnSignIn.click();
    }

    public void openSignOutModal() {
        btnSignOut.click();
    }

    public boolean isSignInButtonVisible() {
        return btnSignIn.state().waitForDisplayed();
    }

    public boolean isSignOutButtonVisible() {
        return btnSignOut.state().waitForDisplayed();
    }

    public void openBreadcrumb(String breadcrumbItemName) {
        getElementFactory().getButton(By.xpath(String.format(BREADCRUMB_XPATH_PATTERN, breadcrumbItemName)), "Breadcrumb - " + breadcrumbItemName).click();
    }

    public void searchFor(String searchItem) {
        txbSearchItem.state().waitForDisplayed();
        txbSearchItem.clearAndType(searchItem);
        btnSearch.click();
    }

    public void openMyBooks() {
        btnMyBooks.click();
    }

    public void openMainPage() {
        btnLogo.click();
    }
}
