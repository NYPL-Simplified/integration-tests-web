package stepdefinitions;

import aquality.selenium.browser.AqualityServices;
import com.google.inject.Inject;
import framework.configuration.Configuration;
import framework.utilities.ScenarioContext;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import pages.CatalogPage;
import pages.SearchResultPage;
import pages.SubcategoryPage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CatalogSteps {
    private Map<String, String> libraryLinks = new HashMap<>();
    private ScenarioContext context;
    private final CatalogPage catalogPage = new CatalogPage();
    private final SubcategoryPage subcategoryPage = new SubcategoryPage();
    private final SearchResultPage searchResultPage = new SearchResultPage();

    @Inject
    public CatalogSteps(ScenarioContext context) {
        this.context = context;
        libraryLinks.put("lyrasis", "lyrasis");
        libraryLinks.put("howard county library system", "howard");
    }

    @And("I open {string} library")
    public void openLibrary(String libraryName) {
        AqualityServices.getBrowser().goTo(Configuration.getStartUrl() + libraryLinks.get(libraryName.toLowerCase()));
    }

    @Then("Library is loaded")
    @And("Books feed is loaded")
    public void checkBooksFeedIsLoaded() {
        Assert.assertTrue(catalogPage.state().waitForDisplayed(), "Books feed is not loaded");
    }

    @When("I get names of books on screen and save them as {string}")
    public void saveNamesOfBooksOnScreen(String listOfBooksKey) {
        context.add(listOfBooksKey, catalogPage.getBooksTitles());
    }

    @And("I open {string} category")
    @When("I open {string} subcategory")
    public void openCategory(String categoryName) {
        catalogPage.openCategory(categoryName);
    }

    @Then("Current category name is {string}")
    public void checkCurrentCategoryNameIsCorrect(String categoryName) {
        Assert.assertEquals(catalogPage.getCategoryName(), categoryName, "Category name is not correct");
    }

    @And("Following subcategories are present:")
    public void checkFollowingSubcategoriesArePresent(List<String> expectedValuesList) {
        Assert.assertTrue(expectedValuesList.stream().allMatch(catalogPage::isSubcategoryPresent), "Not all categories are present");
    }

    @And("List of books on screen is not equal to list of books saved as {string}")
    public void checkListOfBooksOnScreenIsNotEqualToSavedList(String booksNamesListKey) {
        List<String> expectedList = context.get(booksNamesListKey);
        Assert.assertNotEquals(catalogPage.getBooksTitles(), expectedList,
                "Lists of books are equal " + expectedList.stream().map(Object::toString).collect(Collectors.joining(", ")));
    }

    @Then("Subcategory screen is present")
    public void checkSubcategoryScreenIsPresent() {
        Assert.assertTrue(subcategoryPage.state().waitForDisplayed(), "Subcategory screen is not present");
    }

    @And("Subcategory name is {string}")
    public void checkSubcategoryNameIsCorrect(String subcategoryName) {
        Assert.assertEquals(subcategoryPage.getSubcategoryName(), subcategoryName, "Subcategory name is not correct");
    }

    @When("I filter books by {string} format")
    public void filterBooksByFormat(String format) {
        subcategoryPage.sortByFormat(format);
    }

    @And("All present books are audiobooks")
    public void checkAllPresentBooksAreAudiobooks() {
        checkAllBooksTypeIs("audiobook");
    }

    @And("All present books are ebooks")
    public void checkAllPresentBooksAreEbooks() {
        checkAllBooksTypeIs("eBook");
    }

    private void checkAllBooksTypeIs(String bookFormat) {
        Assert.assertTrue(catalogPage.getBooksType().stream().allMatch(x -> x.toLowerCase().contains(bookFormat.toLowerCase())),
                "Not all present books are " + bookFormat + "s");
    }

    @When("I open random book page")
    public void openRandomBookPage() {
        catalogPage.clickRandomVisibleBookCover();
    }

    @And("Count of books in first lane is up to {int}")
    public void countOfBooksInFirstLaneIsUpTo(int countOfBooks) {
        int actualBooksCount = catalogPage.getListOfAllBooksNamesInFirstLane().size();
        Assert.assertTrue(countOfBooks >= actualBooksCount,
                String.format("Count of books is bigger then %d. Actual count - %d", countOfBooks, actualBooksCount));
    }

    @When("I open first book in subcategory list and save it as {string}")
    public void openFirstBookInSubcategoryList(String bookInfoKey) {
        context.add(bookInfoKey, subcategoryPage.openFirstBook());
    }

    @When("I switch to {string} book type in search result")
    public void switchToCatalogTab(String bookType) {
        searchResultPage.selectBookType(bookType);
    }

    @And("I open first book with {string} status")
    public void openFirstBookWithGivenStatus(String bookStatus) {
        searchResultPage.openBookWithStatus(bookStatus);
    }

    @And("Title of all present books contains {string}")
    public void checkTitleOfAllPresentBooksContains(String titlePart) {
        Assert.assertTrue(subcategoryPage.getBookTitles().stream().allMatch(x -> x.toLowerCase().contains(titlePart.toLowerCase())),
                "Not all present books titles contain '" + titlePart + "'");
    }

    @And("Author of all present books is {string}")
    public void checkAuthorOfAllPresentBooksIs(String authorName) {
        Assert.assertTrue(subcategoryPage.getAuthors().stream().allMatch(x -> x.toLowerCase().contains(authorName.toLowerCase())),
                "Not all present books authors contain '" + authorName + "'");
    }
}
