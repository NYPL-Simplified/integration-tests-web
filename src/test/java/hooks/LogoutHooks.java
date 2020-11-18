package hooks;

import aquality.selenium.browser.AqualityServices;
import io.cucumber.java.After;
import org.testng.Assert;
import pages.Header;
import pages.SingOutModal;

public class LogoutHooks {
    private final Header header = new Header();
    private final SingOutModal singOutModal = new SingOutModal();

    @After(value = "@logout", order = 2)
    public void closeApplication() {
        AqualityServices.getLogger().info("Test finished - logging out");
        if (header.isSignOutBtnVisible()) {
            header.openSignOutModal();
            singOutModal.clickSignOut();
            Assert.assertTrue(header.isSignInBtnVisible(), "Sign out was not completed or completed incorrect");
        }
    }
}
