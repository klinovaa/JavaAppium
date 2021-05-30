package lib.ui.ios;

import lib.ui.SearchPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class iOSSearchPageObject extends SearchPageObject
{

    static {
        SEARCH_INIT_ELEMENT = "id:Search Wikipedia";
        SEARCH_INPUT = "xpath://XCUIElementTypeApplication[@name='Wikipedia']";
        SEARCH_CANCEL_BUTTON = "id:Close";
        SEARCH_RESULT_BY_SUBSTRING_TPL = "xpath://XCUIElementTypeStaticText[contains(@name,'{SUBSTRING}')]";
        SEARCH_RESULT_ELEMENT = "xpath://XCUIElementTypeApplication[@name='Wikipedia']/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeCollectionView/XCUIElementTypeCell[1]/XCUIElementTypeOther"; //не знаю, как сократить
        SEARCH_EMPTY_RESULT_ELEMENT = "id:No results found";
        SEARCH_RESULTS_BY_SUBSTRING = "xpath://XCUIElementTypeCell[2]";
        SEARCH_FIELD = SEARCH_INPUT;
    }

    public iOSSearchPageObject(RemoteWebDriver driver)
    {
        super(driver);
    }
}
