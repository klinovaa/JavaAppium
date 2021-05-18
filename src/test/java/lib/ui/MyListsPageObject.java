package lib.ui;

import io.appium.java_client.AppiumDriver;
import lib.Platform;

abstract public class MyListsPageObject extends MainPageObject
{
    protected static String
            FOLDER_BY_NAME_TPL,
            ARTICLE_BY_TITLE_TPL,
            ARTICLE_BY_DESCRIPTION_TPL,
            ARTICLE_TITLE;

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
    /* TEMPLATE METHODS */


    public MyListsPageObject (AppiumDriver driver)
    {
        super(driver);
    }

    public void openFolderByName(String name_of_folder)
    {
        String folder_name_xpath = getFolderXpathByName(name_of_folder);
        this.waitForElementAndClick(folder_name_xpath, "Can't find folder by name " + name_of_folder, 5);
    }

    public void waitForArticleToAppearByTitle(String article_title)
    {
        String article_xpath = getSaveArticleXpathByTitle(article_title);
        this.waitForElementPresent(article_xpath, "Can't find saved article by title " + article_title, 15);
    }

    public void waitForArticleToDisappearByTitle(String article_title)
    {
        String article_xpath = getSaveArticleXpathByTitle(article_title);
        this.waitForElementNotPresent(article_xpath, "Saved article still present with title " + article_title, 15);
    }

    public void waitForArticleToAppearByDescription(String article_description)
    {
        String article_xpath = getSaveArticleXpathByDescription(article_description);
        this.waitForElementPresent(article_xpath, "Can't find saved article by description " + article_description, 15);
    }

    public void swipeByArticleToDelete(String article_title)
    {
        this.waitForArticleToAppearByTitle(article_title);
        String article_xpath = getSaveArticleXpathByTitle(article_title);
        this.swipeElementToLeft(article_xpath, "Can't find saved article");

        if (Platform.getInstance().isIOS()){
            this.clickElementToTheRightUpperCorner(article_xpath, "Can't find saved article");
        }
        this.waitForArticleToDisappearByTitle(article_title);
    }

    public String getArticleTitle()
    {
        if (Platform.getInstance().isAndroid()){
            return this.waitForElementAndGetAttribute(ARTICLE_TITLE, "text", "Can't find title of article when article is in reading list", 15);
        } else {
            return this.waitForElementAndGetAttribute(ARTICLE_TITLE, "name", "Can't find title of article when article is in reading list", 15);
        }
    }

    public void openArticleByDescription(String article_description)
    {
        String article_name_xpath = getSaveArticleXpathByDescription(article_description);
        this.waitForElementAndClick(article_name_xpath, "Can't find title by description " + article_description, 5);
    }
}
