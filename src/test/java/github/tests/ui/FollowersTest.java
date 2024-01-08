package github.tests.ui;

import github.config.ConfigurationManager;
import github.config.GitHubConfig;
import github.helpers.annotations.LocalTestExtensions;
import github.tests.ui.pages.LoginPage;
import github.tests.ui.pages.MainPage;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Configuration.baseUrl;
import static io.qameta.allure.Allure.step;

public class FollowersTest extends TestBase {
    private final LoginPage loginPage = new LoginPage();
    private final GitHubConfig gitHubConfig = ConfigurationManager.getGitHubConfig();

    @Test
    @Severity(SeverityLevel.MINOR)
    @LocalTestExtensions.LocalTest
    @DisplayName("Checking followers")
    void testFindFollower() {
        step("Open login page", () ->
                loginPage.openLoginPage(testData.getUrlLoginPage()));

        step("Enter login", () ->
                loginPage.enterLogin(gitHubConfig.login()));

        step("Enter password", () ->
                loginPage.enterPassword(gitHubConfig.password()));

        step("Click on the login button", loginPage::clickLoginButton);

        step("Checking followers", loginPage::findFollowerTrue);


    }

}
