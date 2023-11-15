package test;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;

@Tags({@Tag("main")})
public class DevqualityTest extends TestBase {

    @DisplayName("Display results when open main page")
    @Test
    void searchResultsCredit() {
        step("Open form devquality", () -> {
            devqualityPage.openPage();
        });

        step("Search about company", () -> {
            devqualityPage.setTextAbout("About us");
        });

        step("Search for guarantee", () -> {
            devqualityPage.setTextGuarantee();
        });
    }

    @DisplayName("Search more information")
    @Test
    void searchResultsInvestments() {
        step("Open form devquality", () -> {
            devqualityPage.openPage();
        });

        step("projects", () -> {
            devqualityPage.setTextProjects();
        });

        step("automationLabel", () -> {
            devqualityPage.setTextAutomation("Automated Testing");
        });
    }
    @DisplayName("Search partners site")
    @Test
    void searchPartnerSite() {
        step("Open form devquality", () -> {
            devqualityPage.openPage();
        });

        step("partner page", () -> {
            devqualityPage.linkGrabr();
        });
    }
    @DisplayName("Search partners in header")
    @Test
    void searchPartnersInHeader() {
        step("Open form devquality", () -> {
            devqualityPage.openPage();
        });

        step("partners in header", () -> {
            devqualityPage.linkHeader();
        });
    }

    @DisplayName("Search for button More")
    @Test
    void searchButtonMore() {
        step("Open form devquality", () -> {
            devqualityPage.openPage();
        });

        step("button more", () -> {
            devqualityPage.setButtonMore();
        });
    }
}