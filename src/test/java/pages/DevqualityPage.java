package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class DevqualityPage {

    private SelenideElement
            about = $x("//h2[text()='About us']"),
            guarantee = $x("//h2[text()='We guarantee']"),
            projects = $x("//h2[text()='Projects']"),
            automationLabel = $x("//div[text()='Automated Testing']"),
            grabrButton = $x("//a[@data-slick-index='2']//img[@class='header__img']"),
            grabrPage = $x("//h1[text()='Shop Anywhere, Travel Everywhere']"),
            header = $x("//header"),
            buttonMore = $x("//div[@class='btn-more__arrows']");




    public DevqualityPage openPage() {
        open("https://devquality.ru/");
        return this;
    }

    public DevqualityPage setTextAbout(String value) {
        about.shouldHave(text(value));
        return this;
    }
    public DevqualityPage setTextGuarantee() {
        guarantee.shouldBe(visible);
        return this;
    }
    public DevqualityPage setTextProjects() {
        projects.shouldBe(visible);
        return this;
    }

    public DevqualityPage setTextAutomation(String value) {
        automationLabel.shouldHave(text(value));
        return this;
    }
    public DevqualityPage linkGrabr() {
        grabrButton.click();
        grabrPage.shouldBe(visible);
        return this;
    }

    public DevqualityPage linkHeader() {
        header.shouldHave(text("Our partners")).click();
        return this;
    }

    public DevqualityPage setButtonMore() {
        buttonMore.click();
        return this;
    }
}