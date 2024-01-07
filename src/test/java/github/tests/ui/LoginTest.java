package github.tests.ui;

import github.config.GitHubConfig;
import github.config.ConfigurationManager;
import io.qameta.allure.*;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import github.helpers.annotations.LocalTestExtensions;
import github.tests.ui.pages.LoginPage;

import static io.qameta.allure.Allure.step;

@Owner("KorolevaEka")
@Epic("Authorization")
@Feature("UI: Authorization")
@DisplayName("Authorization")
public class LoginTest extends TestBase {

    private final GitHubConfig gitHubConfig = ConfigurationManager.getGitHubConfig();
    private final LoginPage loginPage = new LoginPage();

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Login successful")
    void testSuccessfulLogin() {
        step("Open login page", () ->
                loginPage.openLoginPage(testData.getUrlLoginPage()));

        step("Enter login", () ->
                loginPage.enterLogin(gitHubConfig.login()));

        step("Enter password", () ->
                loginPage.enterPassword(gitHubConfig.password()));

        step("Click on the login button", loginPage::clickLoginButton);

        step("Click avatar circle", loginPage::clickAvatarCircle);

        step("Check username", () ->
                loginPage.getUsername("KorolevaEka"));
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @LocalTestExtensions.LocalTest
    @DisplayName("Login with incorrect email")
    void testLoginWithInvalidEmail() {
        step("Open login page", () ->
                loginPage.openLoginPage(testData.getUrlLoginPage()));

        step("Enter incorrect email", () ->
                loginPage.enterLogin("invalidEmail"));

        step("Enter password", () ->
                loginPage.enterPassword(gitHubConfig.password()));

        step("Click on the login button", loginPage::clickLoginButton);

        step("Check warning text", () ->
                Assertions.assertEquals(loginPage.getAlertText(), "Incorrect username or password."));
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @LocalTestExtensions.LocalTest
    @DisplayName("Login with incorrect password")
    void testLoginWithInvalidPassword() {
        step("Open login page", () ->
                loginPage.openLoginPage(testData.getUrlLoginPage()));

        step("Enter login", () ->
                loginPage.enterLogin(gitHubConfig.login()));

        step("Enter the wrong password", () ->
                loginPage.enterPassword("InvalidPassword"));

        step("Click on the login button", loginPage::clickLoginButton);

        step("Check warning text", () ->
                Assertions.assertEquals(loginPage.getAlertText(), "Incorrect username or password."));
    }
}
