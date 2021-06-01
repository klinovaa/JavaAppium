package tests;

import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import lib.CoreTestCase;
import lib.Platform;
import lib.ui.ArticlePageObject;
import lib.ui.SearchPageObject;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Assert;
import org.junit.Test;

public class ChangeAppConditionTests extends CoreTestCase
{
    @Test
    @Features(value = {@Feature(value="Search"), @Feature(value="Article")})
    @DisplayName("Change screen orientation and compare title")
    @Description("We change screen orientation on article page and make sure the title isn't changed")
    @Step("Starting test testChangeScreenOrientationOnSearchResults")
    @Severity(value = SeverityLevel.NORMAL)
    public void testChangeScreenOrientationOnSearchResults()
    {
        if (Platform.getInstance().isMW()){
            return;
        }
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        String title_before_rotation = ArticlePageObject.getArticleTitle();
        this.rotateScreenLandscape();
        String title_after_rotation = ArticlePageObject.getArticleTitle();

        Assert.assertEquals(
                "Article title have been changed after screen rotation",
                title_before_rotation,
                title_after_rotation
        );

        this.rotateScreenPortrait();
        String title_after_second_rotation = ArticlePageObject.getArticleTitle();

        Assert.assertEquals(
                "Article title have been changed after screen rotation",
                title_before_rotation,
                title_after_second_rotation
        );
    }

    @Test
    @Feature(value="Search")
    @DisplayName("Sending app to the background and compare title")
    @Description("We send app to the background and make sure the title isn't changed (not for Mobile Web)")
    @Step("Starting test testCheckSearchArticleInBackground")
    @Severity(value = SeverityLevel.NORMAL)
    public void testCheckSearchArticleInBackground()
    {
        if (Platform.getInstance().isMW()){
            return;
        }
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForSearchResult("Object-oriented programming language");
        this.backgroundApp(2);
        SearchPageObject.waitForSearchResult("Object-oriented programming language");
    }
}
