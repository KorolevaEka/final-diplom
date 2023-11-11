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
            automationLabel = $x("//div[text()='Automated Testing']");



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
        projects.shouldNotBe(visible);
        return this;
    }

    public DevqualityPage setTextAutomation(String value) {
        automationLabel.shouldHave(text(value));
        return this;
    }


}