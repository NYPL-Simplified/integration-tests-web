package hooks;

import aquality.selenium.browser.AqualityServices;
import io.cucumber.java.After;
import org.testng.Assert;
import pages.HeaderPage;
import pages.SingOutModal;

public class LogoutHooks {
    private final HeaderPage headerPage = new HeaderPage();
    private final SingOutModal singOutModal = new SingOutModal();

    @After(value = "@logout", order = 2)
    public void closeApplication() {
        AqualityServices.getLogger().info("Test finished - logging out");
        if (headerPage.isSignOutBtnVisible()) {
            headerPage.openSignOutModal();
            singOutModal.clickSignOut();
            Assert.assertTrue(headerPage.isSignInBtnVisible(), "Sign out was not completed or completed incorrect");
        }
    }
}
