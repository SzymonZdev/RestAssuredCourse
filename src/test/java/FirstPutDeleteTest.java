import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class FirstPutDeleteTest {
    private final String username = "ck_8da07afd3503a4242e95a2d5bad99cc2fb55c552";
    private final String password = "cs_e57e8cadc42ca42064c44239853dda9aa2e77474";
    private final String baseUrl = "http://localhost:10005/wp-json/wc/v3/";
    private final String productEndpoint = "products/";

    @Test
    public void nameChange() {
        Response response = given()
                .auth()
                .oauth(username, password, "", "")
                .contentType("application/json")
                .body("{\"name\": \"I will be changed\"}")

                .when()
                .post(baseUrl + productEndpoint);

        Assertions.assertEquals(201, response.getStatusCode());
        String createdId = response.jsonPath().getString("id");

        response = given()
                .auth()
                .oauth(username, password, "", "")
                .contentType("application/json")
                .body("{\"name\": \"I was changed\"}")

                .when()
                .put(baseUrl + productEndpoint + createdId);

        Assertions.assertEquals(200, response.getStatusCode());
        Assertions.assertEquals("I was changed", response.jsonPath().getString("name"));
    }

    @Test
    public void deleteTest() {
        Response response = given()
                .auth()
                .oauth(username, password, "", "")
                .contentType("application/json")
                .queryParam("force", "true")


                .when()
                .delete(baseUrl + productEndpoint + 977);

        Assertions.assertEquals(200, response.getStatusCode());

        response = given()
                .auth()
                .oauth(username, password, "", "")

                .when()
                .get(baseUrl + productEndpoint + 977);
        
        Assertions.assertEquals(404, response.getStatusCode());
    }
}
