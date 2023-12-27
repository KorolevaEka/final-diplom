package github.tests.ui;

import github.helpers.annotations.LocalTestExtensions;
import github.tests.ui.pages.MainPage;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Configuration.baseUrl;

public class CodeReviewTest extends TestBase {

    private final MainPage mainPage = new MainPage();

    @Test
    @Severity(SeverityLevel.MINOR)
    @DisplayName("Checking of Code Review element")
    void testSuccessfulCodeReviewElement() {
        mainPage.openPage(baseUrl)
                .clickProductButton()
                .checkCodeReview()
                .checkCodeReviewText();
    }

}
