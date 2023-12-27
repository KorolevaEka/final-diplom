package github.tests.api;

import github.data.TestData;
import github.tests.api.models.CreationRepositoryErrorResponse;
import github.tests.api.models.CreationRepositoryRequest;
import github.tests.api.models.CreationRepositoryResponse;
import github.tests.api.managers.RepositoryManager;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static github.tests.api.specs.Specification.*;

@Owner("KorolevaEka")
@Epic("Create repository")
@Feature("API: Create repository")
@Execution(ExecutionMode.CONCURRENT)
@DisplayName("Create repository")
public class CreateRepositoryTest {

    private final TestData testData = new TestData();
    private final RepositoryManager repositoryManager = new RepositoryManager();
    private final String createEndpoint = "user/repos";
    protected String actualRepositoryName;

    @Test
    @DisplayName("Successful creation of the repository")
    void testCreateRepository() {
        step("Creating a request to create a repository", () ->
                step("Submitting a request to create a repository", () -> {
                    CreationRepositoryRequest createRequest = new CreationRepositoryRequest();
                    createRequest.setName(testData.getRepositoryName());
                    createRequest.setDescription(testData.getDescriptionRepository());
                    createRequest.setAutoInit(testData.getAutoInit());

                    CreationRepositoryResponse response = given()
                            .spec(getRequestSpec())
                            .body(createRequest)
                            .when()
                            .post(createEndpoint)
                            .then()
                            .spec(getResponseSpec())
                            .spec(getCreated201Spec())
                            .extract().as(CreationRepositoryResponse.class);
                    actualRepositoryName = createRequest.getName();

                    step("Verifying successful repository creation", () -> {
                        assertThat(response.getName()).isEqualTo(createRequest.getName());
                        assertThat(response.getIsPrivate()).isFalse();
                        assertThat(response.getOwner().getLogin()).isEqualTo(testData.getOwnerName());
                        assertThat(response.getOwner().getUrl()).isEqualTo(String.format("https://api.github.com/users/%s", testData.getOwnerName()));
                    });
                })
        );

        step("Deleting a created repository", () ->
                repositoryManager.deleteRepository(actualRepositoryName)
        );
    }

    @Test
    @DisplayName("Creating a repository with an already existing name")
    void testCreateRepositoryWithExistingName() {
        step("Creating a request to create a repository with an existing name", () ->
                step("Submitting a request to create a repository", () -> {
                    CreationRepositoryRequest createResponse = new CreationRepositoryRequest();
                    createResponse.setName("GitHub_Tester");
                    createResponse.setDescription(testData.getDescriptionRepository());
                    createResponse.setAutoInit(testData.getAutoInit());

                    CreationRepositoryErrorResponse response = given()
                            .spec(getRequestSpec())
                            .body(createResponse)
                            .when()
                            .post(createEndpoint)
                            .then()
                            .spec(getResponseSpec())
                            .spec(getValidationFailed422Spec())
                            .extract().as(CreationRepositoryErrorResponse.class);

                    step("Checking for an error creating a repository with an existing name", () -> {
                        assertThat(response.getErrors().get(0).getMessage()).isEqualTo("name already exists on this account");
                        assertThat(response.getMessage()).isEqualTo("Repository creation failed.");
                    });
                })
        );
    }
}