package tests;

import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import lib.CoreTestCase;
import lib.ui.SearchPageObject;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Assert;
import org.junit.Test;

@Epic("Tests for Search")
public class SearchTests extends CoreTestCase
{
    @Test
    @Feature(value="Search")
    @DisplayName("Search article")
    @Description("We search for article and make sure it's found")
    @Step("Starting test testSearch")
    @Severity(value = SeverityLevel.BLOCKER)
    public void testSearch()
    {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForSearchResult("bject-oriented programming language");
    }

    @Test
    @Feature(value="Search")
    @DisplayName("Cancel Search")
    @Description("We cancel search and make sure it's canceled")
    @Step("Starting test testCancelSearch")
    @Severity(value = SeverityLevel.BLOCKER)
    public void testCancelSearch()
    {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.waitForCancelButtonToAppear();
        SearchPageObject.clickCancelSearch();
        SearchPageObject.waitForCancelButtonToDisappear();
    }

    @Test
    @Feature(value="Search")
    @DisplayName("Search for some results")
    @Description("We search some text and make sure it's not empty")
    @Step("Starting test testAmountOfNotEmptySearch")
    @Severity(value = SeverityLevel.NORMAL)
    public void testAmountOfNotEmptySearch()
    {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        String search_line = "Linkin Park Discography";
        SearchPageObject.typeSearchLine(search_line);
        int amount_of_search_results = SearchPageObject.getAmountOfFoundArticles();

        Assert.assertTrue(
                "We found too few results!",
                amount_of_search_results > 0
        );
    }

    @Test
    @Feature(value="Search")
    @DisplayName("Search for no results")
    @Description("We search inappropriate text and make sure there's no results")
    @Step("Starting test testAmountOfEmptySearch")
    @Severity(value = SeverityLevel.MINOR)
    public void testAmountOfEmptySearch()
    {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        String search_line = "zxcvbsdfghj";
        SearchPageObject.typeSearchLine(search_line);
        SearchPageObject.waitForEmptyResultLabel();
        SearchPageObject.assertThereIsNoResultOfSearch();
    }

    @Test
    @Feature(value="Search")
    @DisplayName("Cancel search when more than 2 articles are found")
    @Description("We search for some text, make sure there's more than 2 results and cancel search")
    @Step("Starting test testCancelSearchWithMoreThanTwoArticles")
    @Severity(value = SeverityLevel.MINOR)
    public void testCancelSearchWithMoreThanTwoArticles()
    {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Testing");
        SearchPageObject.waitForSearchResults();
        SearchPageObject.clearSearchField();
        SearchPageObject.clickCancelSearch();
        SearchPageObject.waitForCancelButtonToDisappear();
    }
}
