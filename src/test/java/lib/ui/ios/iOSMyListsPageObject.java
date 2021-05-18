package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.MyListsPageObject;

public class iOSMyListsPageObject extends MyListsPageObject
{
    static {
        ARTICLE_BY_TITLE_TPL = "xpath://XCUIElementTypeStaticText[@name='{TITLE}']";
        ARTICLE_BY_DESCRIPTION_TPL = "xpath://XCUIElementTypeStaticText[@name='{DESCRIPTION}']";
        ARTICLE_TITLE = "id:JavaScript";
    }

    public iOSMyListsPageObject(AppiumDriver driver)
    {
        super(driver);
    }
}
