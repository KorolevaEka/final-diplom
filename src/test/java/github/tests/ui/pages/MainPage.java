package github.tests.ui.pages;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

@Getter
public class MainPage {

    private final SelenideElement
            searchInput = $x("//span[text()='Search or jump to...']"),
            searchText = $x("//input[@id='query-builder-test']"),
            headerElement = $("[class*=Header-old]"),
            buttonProduct = $x("//header[@role='banner']//button[contains(text(),'Product')]"),
            codeReviewElement = $x("//div[text()='Code review']"),
            codeReviewText = $(byText("Start with a pull request"));
    public MainPage openPage(String baseUrl) {
        open(baseUrl);
        return this;
    }

    public MainPage search() {
        searchInput.click();
        return this;
    }

    public MainPage setDataInSearch(String text) {
        searchText.sendKeys(text);
        searchText.pressEnter();
        return this;
    }

    public MainPage clickProductButton() {
        buttonProduct.shouldHave(text("Product")).click();
        return this;
    }
    public MainPage checkCodeReview() {
        codeReviewElement.click();
        return this;
    }

    public MainPage checkCodeReviewText() {
        codeReviewText.shouldBe(visible);
        return this;
    }
}