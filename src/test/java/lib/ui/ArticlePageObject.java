package lib.ui;

import org.openqa.selenium.WebElement;
import lib.Platform;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class ArticlePageObject extends MainPageObject
{
    protected static String
            TITLE,
            FOOTER_ELEMENT,
            OPTIONS_BUTTON,
            OPTIONS_ADD_TO_MY_LIST_BUTTON,
            OPTIONS_REMOVE_FROM_MY_LIST_BUTTON,
            ADD_TO_MY_LIST_OVERLAY,
            MY_LIST_NAME_INPUT,
            MY_LIST_OK_BUTTON,
            CLOSE_ARTICLE_BUTTON,
            ADD_TO_CREATED_LIST,
            CLOSE_ARTICLE_OVERLAY,
            SEARCH_BUTTON,
            HOME_BUTTON;

    /* TEMPLATE METHODS */
    private static String getCreatedListXpathByName(String name_of_folder)
    {
        return ADD_TO_CREATED_LIST.replace("{NAME_OF_FOLDER}", name_of_folder);
    }
    /* TEMPLATE METHODS */


    public ArticlePageObject(RemoteWebDriver driver)
    {
        super(driver);
    }

    public WebElement waitForTitleElement()
    {
        return this.waitForElementPresent(TITLE, "Can't find article title on page", 15);
    }

    public String getArticleTitle()
    {
        WebElement title_element = waitForTitleElement();
        if (Platform.getInstance().isAndroid()){
            return title_element.getAttribute("text");
        } else if(Platform.getInstance().isAndroid()){
            return title_element.getAttribute("name");
        } else {
            return title_element.getText();
        }
    }

    public void swipeToFooter()
    {
        if (Platform.getInstance().isAndroid()){
            this.swipeUpToFindElement(FOOTER_ELEMENT, "Can't find the end of article", 100);
        } else if (Platform.getInstance().isIOS()){
            this.swipeUpTillElementAppear(FOOTER_ELEMENT, "Can't find the end of article", 40);
        } else {
            this.scrollWebPageTillElementNotVisible(FOOTER_ELEMENT, "Can't find the end of article", 40);
        }
    }

    public void addArticleToMyList(String name_of_folder)
    {
        this.waitForElementAndClick(OPTIONS_BUTTON, "Can't find button to open article options", 5);
        this.waitForElementAndClick(OPTIONS_ADD_TO_MY_LIST_BUTTON, "Can't find option to add article to reading list", 5);
        this.waitForElementAndClick(ADD_TO_MY_LIST_OVERLAY, "Can't find 'Got it' tip overlay", 5);
        this.waitForElementAndClear(MY_LIST_NAME_INPUT, "Can't find input to set name of articles folder", 5);
        this.waitForElementAndSendKeys(MY_LIST_NAME_INPUT, name_of_folder, "Can't put text into articles folder input", 5);
        this.waitForElementAndClick(MY_LIST_OK_BUTTON, "Can't press OK button", 5);
    }

    public void addArticlesToMySaved()
    {
        if(Platform.getInstance().isMW()) {
            this.removeArticleFromSavedIfItAdded();
        }
        this.waitForElementAndClick(OPTIONS_ADD_TO_MY_LIST_BUTTON, "Can't find option to add article to reading list", 10);
    }

    public void closeArticleOverlay()
    {
        this.waitForElementAndClick(CLOSE_ARTICLE_OVERLAY, "Can't close article overlay", 5);
    }

    public void addArticleToCreatedList(String name_of_folder)
    {
        this.waitForElementAndClick(OPTIONS_BUTTON, "Can't find button to open article options", 5);
        this.waitForElementAndClick(OPTIONS_ADD_TO_MY_LIST_BUTTON, "Can't find option to add article to reading list", 5);
        String folder_name_xpath = getCreatedListXpathByName(name_of_folder);
        this.waitForElementAndClick(folder_name_xpath, "Can't find created folder '" + name_of_folder + "' while adding article to reading list", 5);
    }

    public void removeArticleFromSavedIfItAdded(){
        if (this.isElementPresent(OPTIONS_REMOVE_FROM_MY_LIST_BUTTON)){
            this.waitForElementAndClick(OPTIONS_REMOVE_FROM_MY_LIST_BUTTON, "Can't click button to remove an article from saved", 1);
            this.waitForElementPresent(OPTIONS_ADD_TO_MY_LIST_BUTTON, "Can't find button to add an article to saved list after removing it from this list before");
        }
    }

    public void closeArticle()
    {
        if ((Platform.getInstance().isIOS()) || (Platform.getInstance().isAndroid())){
            this.waitForElementAndClick(CLOSE_ARTICLE_BUTTON, "Can't close article, can't find X link", 5);
        } else {
        System.out.println("Method closeArticle() does nothing for platform " + Platform.getInstance().getPlatformVar());
        }
    }

    public void tapToGoHome()
    {
        this.waitForElementAndClick(HOME_BUTTON, "Can't return to main screen", 5);
    }

    public void searchWikipedia()
    {
        this.waitForElementAndClick(SEARCH_BUTTON, "Can't find search button to open search field", 5);
    }

    public void assertArticleTitle()
    {
        this.assertElementPresent(TITLE, "Can't find article title");
    }

}
