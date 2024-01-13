package github.tests.api;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static github.tests.api.specs.Specifications.*;

@Owner("KorolevaEka")
@Epic("List of user repositories")
@Feature("API: List of user repositories")
@DisplayName("List of user repositories")
public class UserRepositoriesTest {

    private final String userRepositoriesEndpoint = "users/KorolevaEka/repos";

    @Test
    @DisplayName("Checking a user's list of repositories")
    void testListOfUserRepositories() {
        step("Getting a list of user repositories", () -> {
            Response response = given(getRequestSpec())
                    .when()
                    .get(userRepositoriesEndpoint)
                    .then()
                    .spec(getOk200Spec())
                    .spec(getResponseSpec())
                    .extract().response();

            step("Checking the owner of the repositories", () -> {
                List<Map<String, ?>> owners = response.path("findAll { it.owner }.owner");
                assertThat(owners)
                        .extracting("login")
                        .containsOnly("KorolevaEka");
            });
        });
    }
}