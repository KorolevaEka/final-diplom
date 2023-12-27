package github.tests.api;

import github.tests.api.managers.RepositoryManager;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import github.data.TestData;
import github.tests.api.models.PatchRepositoryRequest;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static github.tests.api.specs.Specification.*;

@Owner("KorolevaEka")
@Epic("Renaming a repository")
@Feature("API: Renaming a repository")
@DisplayName("Renaming a repository")
public class RenameRepositoryTest {

    private final RepositoryManager repositoryManager = new RepositoryManager();
    private final TestData testData = new TestData();

    @Test
    @DisplayName("Successfully renaming the repository")
    void renameRepository() {
        step("Creating a repository", () -> {
            String repositoryName = repositoryManager.createRepository();
            String renameEndPoint = String.format("repos/%s/%s", testData.getOwnerName(), repositoryName);

            step("Preparing data for renaming the repository", () ->
                    step("Sending a request to rename a repository", () -> {
                        PatchRepositoryRequest patchRepositoryRequest = new PatchRepositoryRequest();
                        patchRepositoryRequest.setName(testData.getNewNameRepository());
                        patchRepositoryRequest.setDescription(testData.getDescriptionRepository());

                        Response response = given()
                                .spec(getRequestSpec())
                                .body(patchRepositoryRequest)
                                .when()
                                .patch(renameEndPoint)
                                .then()
                                .spec(getResponseSpec())
                                .spec(getOk200Spec())
                                .extract().response();

                        String actualRepositoryName = patchRepositoryRequest.getName();

                        step("Verifying that the repository was successfully renamed", () -> {
                            assertThat(response.statusCode()).isEqualTo(200);
                            assertThat(response.jsonPath().getString("name")).isEqualTo(patchRepositoryRequest.getName());
                            assertThat(response.jsonPath().getString("owner.login")).isEqualTo(testData.getOwnerName());
                            assertThat(response.jsonPath().getString("description")).isEqualTo(patchRepositoryRequest.getDescription());
                        });

                        step("Deleting a repository", () ->
                                repositoryManager.deleteRepository(actualRepositoryName)
                        );
                    })
            );
        });
    }
}