package stepdefinitions;

import com.google.inject.Inject;
import constants.pages.BookActionButtons;
import framework.utilities.FileUtils;
import framework.utilities.ScenarioContext;
import io.cucumber.java.DataTableType;
import io.cucumber.java.Transpose;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import models.BookDetailsScreenInformationBlock;
import org.testng.Assert;
import pages.BookPage;

import java.util.Map;

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

    @When("I click {} book action button")
    public void clickBookActionButton(BookActionButtons action) {
        bookPage.clickBookActionBtn(action);
    }

    @Then("Check that {} book button appeared")
    public void checkThatBookActionBtnAppeared(BookActionButtons action) {
        Assert.assertTrue(bookPage.isActionBtnVisible(action), "Download book button appeared");
    }

    @Then("Check the book was downloaded successfully")
    public void checkThatTheBookWasDownloadedSuccessfully() {
        String downloadBookName = bookPage.getDownloadFileName();
        Assert.assertTrue(FileUtils.isFileContainingNameDownloaded(downloadBookName),
                "The book was not downloaded successfully");
    }

    @Then("Book {string} is opened")
    public void checkSavedBookIsOpened(String bookInfoKey) {
        Assert.assertEquals(bookPage.getBookInfo(), context.get(bookInfoKey), "Open book is not correct");
    }

    @Then("Books info screen is present")
    public void checkBooksInfoScreenIsPresent() {
        Assert.assertTrue(bookPage.state().waitForDisplayed(), "Books info screen is present");
    }

    @When("I open {string} recommendation subcategory on book page")
    public void openSubcategoryOnBookPage(String subcategoryName) {
        bookPage.openRecommendationsCategory(subcategoryName);
    }

    @And("Count of books in subcategory {string} lane is up to {int}")
    public void checkCountOfBooksInSubcategoryLaneIsUpTo(String categoryName, int countOfBooks) {
        int actualBooksCount = bookPage.getCountOfBooksInRecommendationsLane(categoryName);
        Assert.assertTrue(countOfBooks >= actualBooksCount,
                String.format("Count of books is bigger then %d. Actual count - %d", countOfBooks, actualBooksCount));
    }

    @And("Description has text")
    public void descriptionHasText(String description) {
        Assert.assertEquals(bookPage.getDescription(), description, "Book description is not correct");
    }

    @And("The following values in the information block are present:")
    public void checkFollowingValuesInTheInformationBlockArePresent(@Transpose BookDetailsScreenInformationBlock bookInfo) {
        Assert.assertEquals(bookPage.getBookDescriptionInfo(), bookInfo, "Information block info is not correct");
    }

    @DataTableType
    public BookDetailsScreenInformationBlock getUserInfo(Map<String, String> entry) {
        BookDetailsScreenInformationBlock bookDetailsScreenInformationBlock = new BookDetailsScreenInformationBlock();
        bookDetailsScreenInformationBlock.setCategories(entry.get("categories"));
        bookDetailsScreenInformationBlock.setPublisher(entry.get("publisher"));
        bookDetailsScreenInformationBlock.setPublished(entry.get("published"));
        return bookDetailsScreenInformationBlock;
    }
}
