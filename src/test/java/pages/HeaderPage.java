package pages;

import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.forms.Form;
import constants.pages.HeaderPageConstants;
import org.openqa.selenium.By;

public class HeaderPage extends Form {
    private static final String LOGIN_LOGOUT_BTN_LOC = "//button[text()='%1$s']";
    private static final String BREADCRUMB_XPATH_PATTERN = "//a[contains(text(),'%s')]";

    private final IButton signInBtn = getElementFactory().getButton(
            By.xpath(String.format(LOGIN_LOGOUT_BTN_LOC, HeaderPageConstants.SIGN_IN)), HeaderPageConstants.SIGN_IN);
    private final IButton signOutBtn = getElementFactory().getButton(
            By.xpath(String.format(LOGIN_LOGOUT_BTN_LOC, HeaderPageConstants.SIGN_OUT)), HeaderPageConstants.SIGN_OUT);

    public HeaderPage() {
        super(By.xpath("//header"), "Header page");
    }

    public void openSignInModal() {
        signInBtn.click();
    }

    public void openSignOutModal() {
        signOutBtn.click();
    }

    public boolean isSignInBtnVisible() {
        return signInBtn.state().waitForDisplayed();
    }

    public boolean isSignOutBtnVisible() {
        return signOutBtn.state().waitForDisplayed();
    }

    public void openBreadcrumb(String breadcrumbItemName) {
        getElementFactory().getButton(By.xpath(String.format(BREADCRUMB_XPATH_PATTERN, breadcrumbItemName)), "Breadcrumb - " + breadcrumbItemName).click();
    }
}
