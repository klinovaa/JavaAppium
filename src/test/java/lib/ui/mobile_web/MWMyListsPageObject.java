package lib.ui.mobile_web;

import lib.ui.MyListsPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWMyListsPageObject extends MyListsPageObject {
    static {
        ARTICLE_BY_TITLE_TPL = "xpath://ul[contains(@class, 'watchlist')]//h3[contains(text(), '{TITLE}']";
        ARTICLE_BY_DESCRIPTION_TPL = "xpath://XCUIElementTypeStaticText[@name='{DESCRIPTION}']"; //?????
        ARTICLE_TITLE = "id:JavaScript";//????
        REMOVE_FROM_SAVED_BUTTON_TPL = "xpath://ul[contains(@class, 'watchlist')]//h3[contains(text(), '{TITLE}']/../../div[contains(@class, 'watched')]";
    }

    public MWMyListsPageObject(RemoteWebDriver driver)
    {
        super(driver);
    }
}
