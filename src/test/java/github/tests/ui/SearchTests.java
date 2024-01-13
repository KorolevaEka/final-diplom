package github.tests.ui;

import github.helpers.annotations.Critical;
import github.tests.ui.pages.MainPage;
import github.tests.ui.pages.SearchPage;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Configuration.baseUrl;
import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.Assertions.assertThat;

@Owner("KorolevaEka")
@Epic("Search check")
@Feature("UI: Search check")
@DisplayName("Search check")
public class SearchTests extends TestBase {

    private final MainPage mainPage = new MainPage();
    private final SearchPage searchPage = new SearchPage();

    @Test
    @Critical
    @DisplayName("Search on the main page")
    void testSearch() {
        step("Open main page", () ->
                mainPage.openPage(baseUrl));

        step("Click and set data in search field", () ->
                mainPage.search()
                        .setDataInSearch(testData.getSearchRepo()));

         step("Check search results", () ->
                searchPage.searchResults(testData.getSearchResult()));
    }

    @Test
    @Critical
    @DisplayName("Advanced search")
    void testAdvancedSearch() {
        step("Open advanced search page", () ->
                searchPage.openAdvancedSearch(testData.getUrlSearchAdvanced()));

        step("Select search language", () ->
                searchPage.clickSearchLanguage()
                        .selectSearchLanguage(testData.getAdvancedSearchLanguage()));

        step("Set search options", () ->
                searchPage.setSearchFrom(testData.getAdvancedSearchFrom())
                        .setSearchDate(testData.getAdvancedSearchDate())
                        .submitAdvancedSearch());

        step("Check search results", () ->
                assertThat(searchPage.isSearchResultDisplayed(testData.getSearchResult()))
                        .isTrue());
    }
}