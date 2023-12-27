package github.tests.ui;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import github.config.ConfigurationManager;
import github.config.SelenoidConfig;
import github.data.TestData;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.openqa.selenium.remote.DesiredCapabilities;
import github.config.WebConfig;
import github.helpers.Attach;

import java.util.Map;

@Execution(ExecutionMode.CONCURRENT)
public class TestBase {

    protected static WebConfig webConfig = ConfigurationManager.getWebConfig();
    protected static SelenoidConfig authSelenoidConfig = ConfigurationManager.getSelenoidConfig();
    protected static boolean isRemote = Boolean.getBoolean("isRemote");
    protected final TestData testData = new TestData();

    @BeforeAll
    static void setUpBase() {
        Configuration.pageLoadStrategy = System.getProperty("selenide.pageLoadStrategy", "eager");
        Configuration.baseUrl = webConfig.baseUrl();
        String[] browserWithVersion = webConfig.browserAndVersion();
        Configuration.browser = browserWithVersion[0];
        Configuration.browserVersion = browserWithVersion[1];
        Configuration.browserSize = webConfig.browserSize();

        if (isRemote) {
            String remoteUrl = authSelenoidConfig.url();
            Configuration.remote = "https://" + authSelenoidConfig.userName() + ":" + authSelenoidConfig.password() + "@" + remoteUrl + "/wd/hub";
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                    "enableVNC", true,
                    "enableVideo", true
            ));
            Configuration.browserCapabilities = capabilities;
        }
    }

    @BeforeEach
    void setUpListener() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterEach
    void tearDownBase() {
        if (isRemote) {
            Attach.addVideo();
        }
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.screenshotAs("Last screenshot");
        Selenide.closeWebDriver();
    }
}