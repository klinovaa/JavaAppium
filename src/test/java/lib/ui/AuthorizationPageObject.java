package lib.ui;

import io.qameta.allure.Step;
import org.openqa.selenium.remote.RemoteWebDriver;

public class AuthorizationPageObject extends MainPageObject {
    private static final String
            LOGIN_BUTTON = "xpath://*[contains(@class,'drawer-container__drawer')]//a[text()='Log in']",//"xpath://a[text()='Log in']",//button[text()='Log in']//a[text()='Log in']
            LOGIN_INPUT = "css:input[name='wpName']",
            PASSWORD_INPUT = "css:input[name='wpPassword']",
            SUBMIT_BUTTON = "css:button#wpLoginAttempt";

    public AuthorizationPageObject(RemoteWebDriver driver){
        super(driver);
    }

    @Step("Clicking Log In button")
    public void clickAuthButton(){
        screenshot(this.takeScreenshot("login_button"));
        this.waitForElementPresent(LOGIN_BUTTON, "Can't find auth button", 10);
        this.waitForElementAndClick(LOGIN_BUTTON, "Can't find and click auth button", 15);
    }

    @Step("Input '{login}' and '{password}' in login and password fields")
    public void enterLoginData(String login, String password){
        this.waitForElementAndSendKeys(LOGIN_INPUT, login, "Can't find and put a login to the login input", 5);
        this.waitForElementAndSendKeys(PASSWORD_INPUT, password, "Can't find and put a password to the password input", 5);
        screenshot(this.takeScreenshot("filled_fields"));
    }

    @Step("Submitting entered data")
    public void submitForm(){
        this.waitForElementAndClick(SUBMIT_BUTTON, "Can't find and click submit auth button", 5);
    }
}
