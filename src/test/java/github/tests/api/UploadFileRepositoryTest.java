package github.tests.api;

import github.data.TestData;
import github.tests.api.managers.RepositoryManager;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import github.tests.api.models.PutFileContentsRepositoryRequest;
import github.utils.Base64Converter;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static github.tests.api.specs.Specifications.*;

@Owner("KorolevaEka")
@Epic("Uploading a file to the repository")
@Feature("API: Uploading a file to the repository")
@DisplayName("Uploading a file to the repository")
public class UploadFileRepositoryTest {

    private final Base64Converter base64Converter = new Base64Converter();
    private final RepositoryManager repositoryManager = new RepositoryManager();
    private final TestData testData = new TestData();

    @Test
    @DisplayName("Successful file upload")
    void uploadFileTest() {
        String repositoryName = repositoryManager.createRepository();
        String endpoint = String.format("repos/%s/%s/contents/%s", testData.getOwnerName(), repositoryName, testData.getNameNewFile());

        step("Preparing data for file upload", () ->
                step("Uploading a file to the repository", () -> {
                    PutFileContentsRepositoryRequest putFileContentsRepositoryRequest = new PutFileContentsRepositoryRequest();
                    putFileContentsRepositoryRequest.setMessage(testData.getMessage());
                    putFileContentsRepositoryRequest.setContent(base64Converter.encodeToBase64(testData.getContent()));

                    String response = given(getRequestSpec())
                            .body(putFileContentsRepositoryRequest)
                            .when()
                            .put(endpoint)
                            .then()
                            .spec(getResponseSpec())
                            .spec(getCreated201Spec())
                            .extract().asString();

                    step("Checking information about an uploaded file", () -> {
                        String contentName = JsonPath.from(response).getString("content.name");
                        String commitMessage = JsonPath.from(response).getString("commit.message");

                        assertThat(contentName).isEqualTo(testData.getNameNewFile());
                        assertThat(commitMessage).isEqualTo(testData.getMessage());
                    });
                })
        );

        step("Deleting a repository", () ->
                repositoryManager.deleteRepository(repositoryName)
        );
    }
}