package lib.ui;

import io.qameta.allure.Step;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class SearchPageObject extends MainPageObject
{
    protected static String
            SEARCH_INIT_ELEMENT,
            SEARCH_INPUT,
            SEARCH_CANCEL_BUTTON,
            SEARCH_RESULT_BY_SUBSTRING_TPL,
            SEARCH_RESULT_ELEMENT,
            SEARCH_EMPTY_RESULT_ELEMENT,
            SEARCH_RESULTS_BY_SUBSTRING,
            SEARCH_FIELD;

    public SearchPageObject (RemoteWebDriver driver)
    {
        super(driver);
    }


    /* TEMPLATE METHODS */
    private static String getResultSearchElement(String substring)
    {
        return SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
    }
    /* TEMPLATE METHODS */

    @Step("Initializing the search field")
    public void initSearchInput()
    {
        this.waitForElementAndClick(SEARCH_INIT_ELEMENT, "Can't find and click search init element", 5);
        this.assertElementPresent(SEARCH_INIT_ELEMENT, "Can't find search input after clicking search init element");
    }

    @Step("Waiting for button to cancel search result")
    public void waitForCancelButtonToAppear()
    {
        this.waitForElementPresent(SEARCH_CANCEL_BUTTON, "Can't find search cancel button", 5);
    }

    @Step("Waiting for search cancel button to be disappear")
    public void waitForCancelButtonToDisappear()
    {
        this.waitForElementNotPresent(SEARCH_CANCEL_BUTTON, "Search cancel button is still present", 5);
    }

    @Step("Clicking button to cancel search result")
    public void clickCancelSearch()
    {
        this.waitForElementAndClick(SEARCH_CANCEL_BUTTON, "Can't find and click search cancel button", 5);
    }

    @Step("Typing '{search_line}' to the search line")
    public void typeSearchLine(String search_line)
    {
        this.waitForElementAndSendKeys(SEARCH_INPUT, search_line, "Can't find and type into search input", 5);
        screenshot(this.takeScreenshot("entered_search_input"));
    }

    @Step("Waiting for search result with substring '{substring}'")
    public void waitForSearchResult(String substring)
    {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementPresent(search_result_xpath, "Can't find search result with substring" + substring);
        screenshot(this.takeScreenshot("search_result"));
    }

    @Step("Waiting for search result and select an article by substring '{substring}' in the article title")
    public void clickByArticleWithSubstring(String substring)
    {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementAndClick(search_result_xpath, "Can't find and click search result with substring" + substring, 10);
    }

    @Step("Getting amount of found articles")
    public int getAmountOfFoundArticles()
    {
        this.waitForElementPresent(SEARCH_RESULT_ELEMENT, "Can't find anything by the request ", 15);
        return this.getAmountOfElements(SEARCH_RESULT_ELEMENT);
    }

    @Step("Waiting for empty result label")
    public void waitForEmptyResultLabel()
    {
        this.waitForElementPresent(SEARCH_EMPTY_RESULT_ELEMENT, "Can't find empty result element", 15);
    }

    @Step("Making sure there are no results for the search")
    public void assertThereIsNoResultOfSearch()
    {
        this.assertElementNotPresent(SEARCH_RESULT_ELEMENT, "We supposed not to find any results");
    }

    @Step("Waiting for search results (more than 1)")
    public void waitForSearchResults()
    {
        this.waitForElementPresent(SEARCH_RESULTS_BY_SUBSTRING, "Can't find more than 1 article", 15);
    }

    @Step("Clearing search field")
    public void clearSearchField()
    {
        this.waitForElementAndClear(SEARCH_FIELD,  "Can't find search field", 10);
    }
}
