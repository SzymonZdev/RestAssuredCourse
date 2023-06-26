import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ExerciseTests {
    private final String username = "ck_8da07afd3503a4242e95a2d5bad99cc2fb55c552";
    private final String password = "cs_e57e8cadc42ca42064c44239853dda9aa2e77474";
    private final String baseUrl = "http://localhost:10005/wp-json/wc/v3/";
    private final String productEndpoint = "products/";
    @Test
    public void postAndGetTest() {
        Response response = given()
                .auth()
                .oauth(username, password, "", "")
                .contentType("application/json")
                .body("{\"name\": \"Test Me 123\"}")

                .when()
                .post(baseUrl + productEndpoint);

        if (response.getStatusCode() == 201) {
            String newId = Integer.toString(response.getBody().path("id"));

            Response getId = given()
                    .auth()
                    .oauth(username, password, "", "")

                    .when()
                    .get(baseUrl + productEndpoint + newId);

            if (getId.getStatusCode() == 200 && getId.jsonPath().get("name").equals("Test me 123")) {
                Response getAll = given()
                        .auth()
                        .oauth(username, password, "", "")
                        .queryParam("per_page", 100)

                        .when()
                        .get(baseUrl + productEndpoint);

                Assertions.assertAll(
                        () -> assertEquals(200, getAll.getStatusCode()),
                        () -> assertTrue(response.prettyPrint().contains(newId)),
                        () -> assertTrue(response.prettyPrint().contains("Test me 123"))
                );
            }
        }
    }
}
