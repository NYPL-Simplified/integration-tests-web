package stepdefinitions;

import com.google.inject.Inject;
import framework.utilities.ScenarioContext;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import models.BookInfo;
import org.testng.Assert;
import pages.BookPage;

public class BookSteps {
    private final BookPage bookPage = new BookPage();
    private ScenarioContext context;

    @Inject
    public BookSteps(ScenarioContext context) {
        this.context = context;
    }

    @Then("Check book page was opened")
    public void checkBookPageWasOpened() {
        Assert.assertTrue(bookPage.state().waitForDisplayed(), "Book page was not opened");
    }

    @When("I click borrow book button")
    public void borrowBook() {
        bookPage.clickBorrowBook();
    }

    @Then("Check that download book button appeared")
    public void checkThatDownloadBookBtnAppeared() {
        Assert.assertTrue(bookPage.isDownloadBookBtnVisible(), "Download book button appeared");
    }

    @Then("Book {string} is opened")
    public void checkSavedBookIsOpened(String bookInfoKey) {
        Assert.assertEquals(bookPage.getBookInfo(), context.get(bookInfoKey), "Open book is not correct");
    }
}
