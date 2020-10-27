package stepdefinitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import pages.BookPage;

public class BookSteps {
    private final BookPage bookPage = new BookPage();

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
}
