package github.tests.ui.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class LoginPage {

    private final SelenideElement
            loginFormHeader = $("[class*=auth-form-header]"),
            body = $("body"),
            loginField = $("#login_field"),
            passwordField = $("#password"),
            loginButton = $("[name=commit]"),
            avatarCircle = $x("//span//img[@class='avatar circle']"),
            userNameString = $x("//span[@class='Truncate-text' and contains(text(),' Kor')]"),
            alertElement = $(".js-flash-alert"),
            findFollower = $(byText("started following"));

    public LoginPage openLoginPage(String urlLoginPage) {
        open(urlLoginPage);
        return this;
    }

    public void clickLoginFormHeader() {
        loginFormHeader.click();
    }

    public SelenideElement getBodyElement() {
        return body;
    }

    public LoginPage enterLogin(String login) {
        loginField.setValue(login);
        return this;
    }

    public LoginPage enterPassword(String password) {
        passwordField.setValue(password);
        return this;
    }

    public LoginPage clickLoginButton() {
        loginButton.click();
        return this;
    }

    public LoginPage clickAvatarCircle() {
        avatarCircle.click();
        return this;
    }

    public LoginPage getUsername(String name) {
        userNameString.shouldHave(Condition.text(name));
        return this;
    }

    public String getAlertText() {
        return alertElement.getText();
    }

    public LoginPage findFollowerTrue() {
        findFollower.shouldBe(Condition.visible);
        return this;
    }
}