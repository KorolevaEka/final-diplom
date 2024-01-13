package github.tests.api.managers;

import github.data.TestData;
import github.tests.api.models.CreationRepositoryRequest;

import static io.restassured.RestAssured.given;
import static github.tests.api.specs.Specifications.getRequestSpec;

public class RepositoryManager {

    private final TestData testData = new TestData();
    private final String deleteEndpoint = String.format("repos/%s/", testData.getOwnerName());

    public String createRepository() {
        CreationRepositoryRequest createRequest = new CreationRepositoryRequest();
        createRequest.setName(testData.getRepositoryName());
        createRequest.setDescription(testData.getDescriptionRepository());
        createRequest.setAutoInit(testData.getAutoInit());

        String createEndpoint = "user/repos";
        given(getRequestSpec())
                .body(createRequest)
                .when()
                .post(createEndpoint)
                .then()
                .statusCode(201);

        String repositoryName = createRequest.getName();
        System.out.println("Create a repository with the name: " + repositoryName);

        return repositoryName;
    }

    public void deleteRepository(String repositoryName) {
        given(getRequestSpec())
                .when()
                .delete(deleteEndpoint + repositoryName)
                .then()
                .statusCode(204);
        System.out.println("Delete the repository with the name: " + repositoryName);
    }
}