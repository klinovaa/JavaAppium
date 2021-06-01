package lib.ui;

import io.qameta.allure.Step;
import lib.Platform;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class NavigationUI extends MainPageObject
{
    protected static String
            MY_LISTS_LINK,
            OPEN_NAVIGATION;

    public NavigationUI(RemoteWebDriver driver)
    {
        super(driver);
    }

    @Step("Opening Navigation menu (Method openNavigation() does nothing for platforms iOS and Android)")
    public void openNavigation()
    {
        if (Platform.getInstance().isMW()){
            this.waitForElementAndClick(OPEN_NAVIGATION, "Can't find and click open navigation button", 5);
        } else {
            System.out.println("Method openNavigation() does nothing for platform " + lib.Platform.getInstance().getPlatformVar());
        }
    }

    @Step("Opening my saved list")
    public void clickMyLists()
    {
        if (Platform.getInstance().isMW()){
            this.tryClickElementWithFewAttempts(MY_LISTS_LINK, "Can't find navigation button to my list", 5);
        } else {
            this.waitForElementAndClick(MY_LISTS_LINK, "Can't find navigation button to my list", 5);
        }
    }
}
