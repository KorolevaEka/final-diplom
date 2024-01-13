package github.tests.api;

import github.data.TestData;
import github.tests.api.managers.RepositoryManager;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import github.utils.ApiZipFileManager;

import static github.tests.api.specs.Specifications.*;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

@Owner("KorolevaEka")
@Epic("Reading and Downloading Repository Contents")
@Feature("API: Reading and Downloading Repository Contents")
@DisplayName("Reading and Downloading Repository Contents")
public class RepositoryZipDownloadAndReadTests {

    private final ApiZipFileManager apiZipFileManager = new ApiZipFileManager();
    private final RepositoryManager repositoryManager = new RepositoryManager();
    private final TestData testData = new TestData();
    private String repositoryName;

    @BeforeEach
    void createRepository() {
        step("Creating a repository", () ->
                repositoryName = repositoryManager.createRepository()
        );
    }

    @AfterEach
    void deleteRepository() {
        step("Deleting a repository", () ->
                repositoryManager.deleteRepository(repositoryName)
        );
    }

    @Test
    @DisplayName("Download and read the contents of a README file from a ZIP archive")
    void testDownloadAndReadFileContentFromZipReadme() {
        String endpoint = String.format("repos/%s/%s/zipball/main", testData.getOwnerName(), repositoryName);

        step("Downloading the repository ZIP archive", () ->
                step("Reading the contents of a README file from a ZIP archive", () -> {
                    Response response = given()
                            .spec(getRequestSpec())
                            .when()
                            .get(endpoint)
                            .then()
                            .spec(getOk200Spec())
                            .spec(getResponseSpec())
                            .extract().response();

                    String fileContent = apiZipFileManager.readFileContentFromZip(response, "README.md");

                    step("Checking the contents of the README file", () -> {
                        assertThat(fileContent)
                                .isNotNull()
                                .contains(repositoryName);
                    });
                })
        );
    }

    @Test
    @DisplayName("Downloading and reading the contents of a non-existent file from a ZIP archive")
    void testDownloadAndReadNonexistentFileFromZip() {
        String endpoint = String.format("repos/%s/%s/zipball/main", testData.getOwnerName(), repositoryName);

        step("Downloading the repository ZIP archive", () ->
                step("Reading the contents of a non-existent file from a ZIP archive", () -> {
                    Response response = given()
                            .spec(getRequestSpec())
                            .when()
                            .get(endpoint)
                            .then()
                            .spec(getOk200Spec())
                            .spec(getResponseSpec())
                            .extract()
                            .response();

                    String fileContent = apiZipFileManager.readFileContentFromZip(response, "nonexistent-file.txt");

                    step("Checking the contents of a non-existent file", () -> {
                        assertThat(fileContent).isEqualTo("File nonexistent-file.txt not found in ZIP archive");
                    });
                })
        );
    }
}