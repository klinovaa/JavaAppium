package lib.ui.android;

import lib.ui.MyListsPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class AndroidMyListsPageObject extends MyListsPageObject
{
    static {
            FOLDER_BY_NAME_TPL = "xpath://*[@text='{FOLDER_NAME}']";
            ARTICLE_BY_TITLE_TPL = "xpath://*[@text='{TITLE}']";
            ARTICLE_BY_DESCRIPTION_TPL = "xpath://*[@text='{DESCRIPTION}']";
            ARTICLE_TITLE = "id:org.wikipedia:id/page_list_item_title";
    }

    public AndroidMyListsPageObject (RemoteWebDriver driver)
    {
        super(driver);
    }
}
