package github.tests.api.specs;

import github.config.GitHubConfig;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import github.config.ConfigurationManager;

import static io.restassured.RestAssured.expect;
import static io.restassured.RestAssured.with;
import static io.restassured.filter.log.LogDetail.BODY;
import static io.restassured.filter.log.LogDetail.STATUS;
import static io.restassured.http.ContentType.JSON;
import static github.helpers.CustomApiListener.withCustomTemplates;

public class Specification {

    public static RequestSpecification getRequestSpec() {

        GitHubConfig GitHubConfig = ConfigurationManager.getGitHubConfig();
        String accessToken = GitHubConfig.token();

        return with()
                .baseUri("https://api.github.com")
                .header("Authorization", "token " + accessToken)
                .log().all()
                .filter(withCustomTemplates())
                .contentType(JSON);
    }

    public static ResponseSpecification getResponseSpec() {
        return new ResponseSpecBuilder()
                .log(STATUS)
                .log(BODY)
                .build();
    }

    public static ResponseSpecification getOk200Spec() {
        return new ResponseSpecBuilder()
                .expectStatusCode(200)
                .build();
    }

    public static ResponseSpecification getCreated201Spec() {
        return expect()
                .statusCode(201);
    }

    public static ResponseSpecification getValidationFailed422Spec() {
        return new ResponseSpecBuilder()
                .expectStatusCode(422)
                .build();
    }
}