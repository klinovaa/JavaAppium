package tests;

import lib.CoreTestCase;
import lib.Platform;
import lib.ui.ArticlePageObject;
import lib.ui.MyListsPageObject;
import lib.ui.NavigationUI;
import lib.ui.SearchPageObject;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.MyListsPageObjectFactory;
import lib.ui.factories.NavigationUIFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

public class MyListsTests extends CoreTestCase
{
    private static final String
                                name_of_folder = "Learning programming",
                                search_line = "Java";

    @Test
    public void testSaveFirstArticleToMyList()
    {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        ArticlePageObject.waitForTitleElement();
        String article_title = ArticlePageObject.getArticleTitle();

        if (Platform.getInstance().isAndroid()) {
            ArticlePageObject.addArticleToMyList(name_of_folder);
        } else {
            ArticlePageObject.addArticlesToMySaved();
            ArticlePageObject.closeArticleOverlay();
        }
        ArticlePageObject.closeArticle();

        NavigationUI NavigationUI = NavigationUIFactory.get(driver);
        NavigationUI.clickMyLists();

        MyListsPageObject MyListsPageObject = MyListsPageObjectFactory.get(driver);
        if (Platform.getInstance().isAndroid()) {
            MyListsPageObject.openFolderByName(name_of_folder);
        }
        MyListsPageObject.swipeByArticleToDelete(article_title);
    }

    @Test
    public void testSaveTwoArticlesToMyList() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(search_line);
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        ArticlePageObject.waitForTitleElement();
        String article_title = ArticlePageObject.getArticleTitle();

        if (Platform.getInstance().isAndroid()) {
            ArticlePageObject.addArticleToMyList(name_of_folder);
            ArticlePageObject.closeArticle();
            SearchPageObject.initSearchInput();
            SearchPageObject.typeSearchLine(search_line);
        } else {
            ArticlePageObject.addArticlesToMySaved();
            ArticlePageObject.closeArticleOverlay();
            ArticlePageObject.searchWikipedia();
        }

        SearchPageObject.clickByArticleWithSubstring("Programming language");

        ArticlePageObject.waitForTitleElement();
        if (Platform.getInstance().isAndroid()) {
            ArticlePageObject.addArticleToCreatedList(name_of_folder);
            ArticlePageObject.closeArticle();
        } else {
            ArticlePageObject.addArticlesToMySaved();
            ArticlePageObject.tapToGoHome();
        }

        NavigationUI NavigationUI = NavigationUIFactory.get(driver);
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
            assertEquals(
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
            assertEquals(
                    "Article title have been changed after opening the article",
                    title_of_article_before_opening,
                    title_of_article_after_opening
            );
        }
        /*отрефакторить?*/
    }
}
