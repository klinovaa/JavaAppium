package tests;

import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import lib.CoreTestCase;
import lib.Platform;
import lib.ui.*;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.MyListsPageObjectFactory;
import lib.ui.factories.NavigationUIFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Assert;
import org.junit.Test;

public class MyListsTests extends CoreTestCase
{
    private static final String
                                name_of_folder = "Learning programming",
                                search_line = "Java",
                                login = "TestWikiProject",
                                password = "qWerty123456";

    @Test
    @Features(value = {@Feature(value="Search"), @Feature(value="Article"), @Feature(value="Navigation"), @Feature(value="Authorization"), @Feature(value="MyList")})
    @DisplayName("Save article to my list and delete it")
    @Description("We save article to my list, make sure it's saved and delete it from saved list")
    @Step("Starting test testSaveFirstArticleToMyList")
    @Severity(value = SeverityLevel.CRITICAL)
    public void testSaveFirstArticleToMyList()
    {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("bject-oriented programming language");

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        ArticlePageObject.waitForTitleElement();
        String article_title = ArticlePageObject.getArticleTitle();

        if (Platform.getInstance().isAndroid()) {
            ArticlePageObject.addArticleToMyList(name_of_folder);
        } else if (Platform.getInstance().isIOS()) {
            ArticlePageObject.addArticlesToMySaved();
            ArticlePageObject.closeArticleOverlay();
        } else {
            ArticlePageObject.addArticlesToMySaved();

            AuthorizationPageObject Auth = new AuthorizationPageObject(driver);
            Auth.clickAuthButton();
            Auth.enterLoginData(login, password);
            Auth.submitForm();

            ArticlePageObject.waitForTitleElement();

            Assert.assertEquals("We are not on the same page after login", article_title, ArticlePageObject.getArticleTitle());
        }
        ArticlePageObject.closeArticle();

        NavigationUI NavigationUI = NavigationUIFactory.get(driver);
        NavigationUI.openNavigation();
        NavigationUI.clickMyLists();

        MyListsPageObject MyListsPageObject = MyListsPageObjectFactory.get(driver);
        if (Platform.getInstance().isAndroid()) {
            MyListsPageObject.openFolderByName(name_of_folder);
        }
        MyListsPageObject.swipeByArticleToDelete(article_title);
    }

    @Test
    @Features(value = {@Feature(value="Search"), @Feature(value="Article"), @Feature(value="Navigation"), @Feature(value="Authorization"), @Feature(value="MyList")})
    @DisplayName("Save two articles to my list, delete one of them")
    @Description("We save two articles to my list, make sure they are saved, delete one of them from saved and make sure another is stayed")
    @Step("Starting test testSaveTwoArticlesToMyList")
    @Severity(value = SeverityLevel.CRITICAL)
    public void testSaveTwoArticlesToMyList() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(search_line);
        SearchPageObject.clickByArticleWithSubstring("bject-oriented programming language");

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        ArticlePageObject.waitForTitleElement();
        String article_title = ArticlePageObject.getArticleTitle();

        if (Platform.getInstance().isAndroid()) {
            ArticlePageObject.addArticleToMyList(name_of_folder);
            ArticlePageObject.closeArticle();
            SearchPageObject.initSearchInput();
            SearchPageObject.typeSearchLine(search_line);
        } else if (Platform.getInstance().isIOS()){
            ArticlePageObject.addArticlesToMySaved();
            ArticlePageObject.closeArticleOverlay();
            ArticlePageObject.searchWikipedia();
        } else {
            ArticlePageObject.addArticlesToMySaved();

            AuthorizationPageObject Auth = new AuthorizationPageObject(driver);
            Auth.clickAuthButton();
            Auth.enterLoginData(login, password);
            Auth.submitForm();

            ArticlePageObject.waitForTitleElement();
            Assert.assertEquals("We are not on the same page after login", article_title, ArticlePageObject.getArticleTitle());
            ArticlePageObject.searchWikipedia();

            SearchPageObject.initSearchInput();
            SearchPageObject.typeSearchLine(search_line);
        }

        SearchPageObject.clickByArticleWithSubstring("Programming language");

        ArticlePageObject.waitForTitleElement();
        if (Platform.getInstance().isAndroid()) {
            ArticlePageObject.addArticleToCreatedList(name_of_folder);
            ArticlePageObject.closeArticle();
        } else if (Platform.getInstance().isMW()){
            ArticlePageObject.addArticlesToMySaved();
            ArticlePageObject.tapToGoHome();
        } else {
            ArticlePageObject.addArticlesToMySaved();
        }

        NavigationUI NavigationUI = NavigationUIFactory.get(driver);
        NavigationUI.openNavigation();
        NavigationUI.clickMyLists();

        MyListsPageObject MyListsPageObject = MyListsPageObjectFactory.get(driver);
        if (Platform.getInstance().isAndroid()) {
            MyListsPageObject.openFolderByName(name_of_folder);
        }
        MyListsPageObject.swipeByArticleToDelete(article_title);

        /*отрефакторить?*/
        if (Platform.getInstance().isAndroid()) {
            String article_description = "programming language";
            MyListsPageObject.waitForArticleToAppearByDescription(article_description);
            String title_of_article_before_opening = MyListsPageObject.getArticleTitle();
            MyListsPageObject.openArticleByDescription(article_description);
            String title_of_article_after_opening = ArticlePageObject.getArticleTitle();
            Assert.assertEquals(
                    "Article title have been changed after opening the article",
                    title_of_article_before_opening,
                    title_of_article_after_opening
            );
        } else {
            String article_description = "High-level programming language";
            MyListsPageObject.waitForArticleToAppearByDescription(article_description);
            String title_of_article_before_opening = MyListsPageObject.getArticleTitle();
            MyListsPageObject.openArticleByDescription(article_description);
            String title_of_article_after_opening = MyListsPageObject.getArticleTitle();
            Assert.assertEquals(
                    "Article title have been changed after opening the article",
                    title_of_article_before_opening,
                    title_of_article_after_opening
            );
        }
        /*отрефакторить?*/
    }
}
