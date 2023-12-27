package github.tests.ui.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import java.io.File;
import java.io.FileNotFoundException;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class RepositoryActionsPage {

    private final SelenideElement
            codeButton = $("[class*='Button--primary']"),
            downloadZipButton = $("[href*='.zip']");

    @Step("Open the repository page {searchQuery}")
    public void openMainPage(String searchQuery) {
        open(String.format("https://github.com/%s", searchQuery));
    }

    @Step("Press the button '<> Code'")
    public void clickCodeButton() {
        codeButton.click();
    }

    @Step("Press the button 'Download ZIP'")
    public File clickDownloadZipButton() throws FileNotFoundException {
        return downloadZipButton.download();
    }
}