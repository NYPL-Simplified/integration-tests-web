package hooks;

import aquality.selenium.browser.AqualityServices;
import com.google.inject.Inject;
import constants.context.ContextLibrariesKeys;
import framework.utilities.ScenarioContext;
import io.cucumber.java.After;
import models.BookInfo;
import org.testng.Assert;
import pages.Header;
import pages.SingOutModal;
import pages.SubcategoryPage;

import java.util.List;
import java.util.Optional;

public class LogoutHooks {
    private final Header header = new Header();
    private final SingOutModal singOutModal = new SingOutModal();
    private final SubcategoryPage subcategoryPage = new SubcategoryPage();
    private ScenarioContext context;

    @Inject
    public LogoutHooks(ScenarioContext context) {
        this.context = context;
    }

    @After(value = "@logout", order = 2)
    public void closeApplication() {
        AqualityServices.getLogger().info("Test finished - logging out");
        if (header.isSignOutBtnVisible()) {
            header.openSignOutModal();
            singOutModal.clickSignOut();
            Assert.assertTrue(header.isSignInBtnVisible(), "Sign out was not completed or completed incorrect");
        }
    }

    @After(value = "@cancelHold", order = 3)
    public void cancelHold() {
        AqualityServices.getLogger().info("Test finished - canceling hold");
        List<BookInfo> booksForCancel = context.get(ContextLibrariesKeys.CANCEL_HOLD);
        header.openMyBooks();
        Optional.ofNullable(booksForCancel).ifPresent(books ->
                books.forEach(book -> {
                    subcategoryPage.cancelReservationForBook(book);
                    AqualityServices.getConditionalWait().waitFor(() -> !subcategoryPage.isBookPresent(book));
                }));
    }
}
