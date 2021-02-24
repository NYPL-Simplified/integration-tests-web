package stepdefinitions;

import aquality.selenium.browser.AqualityServices;
import com.google.inject.Inject;
import constants.context.ContextLibrariesKeys;
import constants.pages.BookActionButtons;
import constants.pages.BookTypeFormatMatch;
import constants.pages.StringConstants;
import framework.utilities.FileUtils;
import framework.utilities.ScenarioContext;
import io.cucumber.java.DataTableType;
import io.cucumber.java.Transpose;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import models.BookDetailsScreenInformationBlock;
import models.BookInfo;
import models.BookLabelsVisibility;
import org.apache.commons.lang3.StringUtils;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import pages.BookPage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
        if (action == BookActionButtons.RESERVE) {
            saveBookInContext(ContextLibrariesKeys.CANCEL_HOLD, bookPage.getBookInfo());
        }
        if (action == BookActionButtons.BORROW) {
            saveBookInContext(ContextLibrariesKeys.CANCEL_BORROW, bookPage.getBookInfo());
        }
        bookPage.clickBookActionButton(action);
    }

    @When("I download book")
    public void downloadBook() {
        if (bookPage.isActionButtonVisible(BookActionButtons.DOWNLOAD_EPUB)) {
            bookPage.clickBookActionButton(BookActionButtons.DOWNLOAD_EPUB);
        } else {
            bookPage.clickBookActionButton(BookActionButtons.DOWNLOAD_EPUB_ADOBE);
        }
    }

    @Then("Check that {} book button appeared")
    public void checkThatBookActionButtonAppeared(BookActionButtons action) {
        Assert.assertTrue(bookPage.isActionButtonVisible(action), "Download book button appeared");
    }

    @Then("Check that download book button is present")
    public void checkThatDownloadBookActionButtonAppeared() {
        Assert.assertTrue(AqualityServices.getConditionalWait().waitFor(() -> bookPage.isActionButtonVisible(BookActionButtons.DOWNLOAD_EPUB) || bookPage.isActionButtonVisible(BookActionButtons.DOWNLOAD_EPUB_ADOBE)), "Download book button appeared");
    }

    @Then("Check the book was downloaded successfully")
    public void checkThatTheBookWasDownloadedSuccessfully() {
        String downloadBookName = bookPage.getDownloadFileName();
        Assert.assertTrue(FileUtils.isFileContainingNameDownloaded(downloadBookName),
                "The book was not downloaded successfully");
    }

    @Then("Book {string} is opened")
    public void checkSavedBookIsOpened(String bookInfoKey) {
        bookPage.state().waitForDisplayed();
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

    @And("Count of books in subcategory {string} lane is more than {int}")
    public void checkCountOfBooksInSubcategoryLaneIsUpTo(String categoryName, int countOfBooks) {
        int actualBooksCount = bookPage.getCountOfBooksInRecommendationsLane(categoryName);
        Assert.assertTrue(countOfBooks < actualBooksCount,
                String.format("Count of books is smaller than %d. Actual count - %d", countOfBooks, actualBooksCount));
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

    @And("Book status is {string}")
    public void checkBookStatusIsReserved(String bookStatus) {
        Assert.assertEquals(bookPage.getStatus(), bookStatus, "Book status is not correct");
    }

    @And("Message {string} is present")
    public void checkMessageAboutPatronsInTheQueueIsPresent(String message) {
        Assert.assertTrue(bookPage.getPatronMessage().contains(message),
                String.format("Queue status is not correct. Expected '%s', actual - '%s'", message, bookPage.getPatronMessage()));
    }

    @Then("Check the book {string} was downloaded successfully")
    public void checkTheBookBookInfoWasDownloadedSuccessfully(String bookInfoKey) {
        BookInfo book = context.get(bookInfoKey);
        Assert.assertTrue(FileUtils.isFileContainingNameDownloaded(Arrays.stream(book.getTitle().split(StringUtils.SPACE))
                        .map(String::toLowerCase)
                        .collect(Collectors.joining(StringConstants.FILE_NAME_DELIMITER))
                        + StringConstants.FILE_EXTENSION_DELIMITER
                        + BookTypeFormatMatch.EPUB.getFileFormat()),
                "The book was not downloaded successfully");
    }

    @Then("Following buttons are present:")
    public void checkFollowingButtonsArePresent(List<Map<String, String>> entries) {
        Map<String, String> entry = entries.get(0);
        BookLabelsVisibility visibility = new BookLabelsVisibility();
        visibility.setDownloadAdobeACSM(Boolean.parseBoolean(entry.get("downloadAdobeACSM")));
        visibility.setDownloadEPUB(Boolean.parseBoolean(entry.get("downloadEPUB")));
        visibility.setReadyToListenOnSimplyEMessage(Boolean.parseBoolean(entry.get("readyToListenOnSimplyEMessage")));
        visibility.setReadyToReadOnSimplyEMessage(Boolean.parseBoolean(entry.get("readyToReadOnSimplyEMessage")));
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(bookPage.isReadyToReadOnSimplyEMessagePresent(), visibility.isReadyToReadOnSimplyEMessage(), "Ready To Read On SimplyE Message" + " label/button is not in expected state");
        softAssert.assertEquals(bookPage.isActionButtonVisible(BookActionButtons.DOWNLOAD_EPUB_ADOBE), visibility.isDownloadAdobeACSM(), "Download Adobe SCM" + " label/button is not in expected state");
        softAssert.assertEquals(bookPage.isReadyToListenOnSimplyEMessagePresent(), visibility.isReadyToListenOnSimplyEMessage(), "Ready To Listen On SimplyE" + " label/button is not in expected state");
        softAssert.assertEquals(bookPage.isActionButtonVisible(BookActionButtons.DOWNLOAD_EPUB), visibility.isDownloadEPUB(), "Download EPUB" + " label/button is not in expected state");
        softAssert.assertAll();
    }

    @Then("Book is not available for loan")
    public void bookIsNotAvailableForLoan() {
        Assert.assertFalse(bookPage.isActionButtonVisible(BookActionButtons.RESERVE) || bookPage.isActionButtonVisible(BookActionButtons.BORROW), "Book is available for loan/reserve");
    }

    private void saveBookInContext(String key, BookInfo bookName) {
        List<BookInfo> listOfLibraries = context.containsKey(key)
                ? context.get(key)
                : new ArrayList<>();

        listOfLibraries.add(bookName);
        context.add(key, listOfLibraries);
    }
}
