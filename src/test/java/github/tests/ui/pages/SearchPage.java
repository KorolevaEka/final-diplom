package github.tests.ui.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.*;

public class SearchPage {

    private final SelenideElement
            searchFromInput = $("#search_from"),
            searchDateInput = $("#search_date"),
            searchLanguageDropdown = $("#search_language"),
            searchButton = $x("//button[@type='submit' and contains (text(),'Search')]");
    private final ElementsCollection searchResultContainer = $$x("//div[@data-testid='results-list']");

    public SearchPage openAdvancedSearch(String urlSearchAdvanced) {
        open(urlSearchAdvanced);
        return this;
    }

    public boolean isSearchResultDisplayed(String resultText) {
        return searchResultContainer.stream().anyMatch(element -> element.text().contains(resultText));
    }

    public SearchPage clickSearchLanguage() {
        searchLanguageDropdown.click();
        return this;
    }

    public SearchPage setSearchFrom(String searchFrom) {
        searchFromInput.setValue(searchFrom).click();
        return this;
    }

    public SearchPage setSearchDate(String searchDate) {
        searchDateInput.setValue(searchDate).click();
        return this;
    }

    public void submitAdvancedSearch() {
        searchButton.click();
    }

    public SearchPage selectSearchLanguage(String language) {
        String searchValueSelector = "[value=%s]";
        $(String.format(searchValueSelector, language)).click();
        return this;
    }
}