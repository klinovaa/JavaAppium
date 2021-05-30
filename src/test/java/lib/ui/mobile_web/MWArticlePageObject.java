package lib.ui.mobile_web;

import lib.ui.ArticlePageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWArticlePageObject extends ArticlePageObject {
    static {
        TITLE = "css:#content h1";
        FOOTER_ELEMENT = "css:div>footer";
        OPTIONS_ADD_TO_MY_LIST_BUTTON = "css:#page-actions #ca-watch[class*='mw-ui-icon-wikimedia-star'][role='button']";//"a.watch-this-article";//"css:#page-actions-watch a#ca-watch.mw-ui-icon-wikimedia-star-base20";
        OPTIONS_REMOVE_FROM_MY_LIST_BUTTON = "css:#page-actions-watch a#ca-watch.mw-ui-icon-wikimedia-unStar-progressive";
        SEARCH_BUTTON = "css:button#searchIcon";
        HOME_BUTTON = "xpath://XCUIElementTypeButton[@name='Wikipedia, return to Explore']";//???????
    }

    public MWArticlePageObject(RemoteWebDriver driver)
    {
        super(driver);
    }
}
