package lib.ui.ios;

import lib.ui.ArticlePageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class iOSArticlePageObject extends ArticlePageObject {
    static {
        TITLE = "id:Java (programming language)";
        FOOTER_ELEMENT = "id:View article in browser";
        OPTIONS_ADD_TO_MY_LIST_BUTTON = "id:Save for later";
        CLOSE_ARTICLE_OVERLAY = "id:places auth close";
        CLOSE_ARTICLE_BUTTON = "id:Back";
        SEARCH_BUTTON = "id:Search Wikipedia";
        HOME_BUTTON = "xpath://XCUIElementTypeButton[@name='Wikipedia, return to Explore']";
    }

    public iOSArticlePageObject(RemoteWebDriver driver)
    {
        super(driver);
    }
}
