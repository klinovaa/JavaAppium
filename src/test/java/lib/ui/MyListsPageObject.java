package lib.ui;

import io.qameta.allure.Step;
import lib.Platform;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class MyListsPageObject extends MainPageObject
{
    protected static String
            FOLDER_BY_NAME_TPL,
            ARTICLE_BY_TITLE_TPL,
            ARTICLE_BY_DESCRIPTION_TPL,
            ARTICLE_TITLE,
            REMOVE_FROM_SAVED_BUTTON_TPL;

    /* TEMPLATE METHODS */
    private static String getFolderXpathByName(String name_of_folder)
    {
        return FOLDER_BY_NAME_TPL.replace("{FOLDER_NAME}", name_of_folder);
    }

    private static String getSaveArticleXpathByTitle(String article_title)
    {
        return ARTICLE_BY_TITLE_TPL.replace("{TITLE}", article_title);
    }

    private static String getSaveArticleXpathByDescription(String article_description)
    {
        return ARTICLE_BY_DESCRIPTION_TPL.replace("{DESCRIPTION}", article_description);
    }

    private static String getRemoveButtonByTitle(String article_title)
    {
        return REMOVE_FROM_SAVED_BUTTON_TPL.replace("{TITLE}", article_title);
    }

    /* TEMPLATE METHODS */


    public MyListsPageObject (RemoteWebDriver driver)
    {
        super(driver);
    }

    @Step("Opening folder with name '{name_of_folder}'")
    public void openFolderByName(String name_of_folder)
    {
        String folder_name_xpath = getFolderXpathByName(name_of_folder);
        this.waitForElementAndClick(folder_name_xpath, "Can't find folder by name " + name_of_folder, 5);
        screenshot(this.takeScreenshot("folder_is_open"));
    }

    @Step("Waiting for article with title '{article_title}'")
    public void waitForArticleToAppearByTitle(String article_title)
    {
        String article_xpath = getSaveArticleXpathByTitle(article_title);
        this.waitForElementPresent(article_xpath, "Can't find saved article by title " + article_title, 15);
        screenshot(this.takeScreenshot("article_by_title"));
    }

    @Step("Waiting for article with title '{article_title}' to disappear")
    public void waitForArticleToDisappearByTitle(String article_title)
    {
        String article_xpath = getSaveArticleXpathByTitle(article_title);
        this.waitForElementNotPresent(article_xpath, "Saved article still present with title " + article_title, 15);
    }

    @Step("Waiting for article with description '{article_description}'")
    public void waitForArticleToAppearByDescription(String article_description)
    {
        String article_xpath = getSaveArticleXpathByDescription(article_description);
        this.waitForElementPresent(article_xpath, "Can't find saved article by description " + article_description, 15);
        screenshot(this.takeScreenshot("article_by_description"));
    }

    @Step("Deleting article from my list")
    public void swipeByArticleToDelete(String article_title)
    {
        this.waitForArticleToAppearByTitle(article_title);
        String article_xpath = getSaveArticleXpathByTitle(article_title);

        if ((Platform.getInstance().isIOS()) || (Platform.getInstance().isAndroid()))
        {
            this.swipeElementToLeft(article_xpath, "Can't find saved article");
        } else {
            String remove_locator = getRemoveButtonByTitle(article_title);
            this.waitForElementAndClick(remove_locator, "Can't click button to remove article from saved", 10);
        }

        if (Platform.getInstance().isIOS()){
            this.clickElementToTheRightUpperCorner(article_xpath, "Can't find saved article");
        }

        if (Platform.getInstance().isMW()){
            driver.navigate().refresh();
        }

        this.waitForArticleToDisappearByTitle(article_title);
        screenshot(this.takeScreenshot("article_is_deleted"));
    }

    @Step("Getting article title and make sure it's correct")
    public String getArticleTitle()
    {
        if (Platform.getInstance().isAndroid()){
            return this.waitForElementAndGetAttribute(ARTICLE_TITLE, "text", "Can't find title of article when article is in reading list", 15);
        } else {
            return this.waitForElementAndGetAttribute(ARTICLE_TITLE, "name", "Can't find title of article when article is in reading list", 15);
        }
    }

    @Step("Opening article with description '{article_description}'")
    public void openArticleByDescription(String article_description)
    {
        String article_name_xpath = getSaveArticleXpathByDescription(article_description);
        this.waitForElementAndClick(article_name_xpath, "Can't find title by description " + article_description, 5);
    }
}
