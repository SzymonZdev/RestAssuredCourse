package OldTests;

import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class FirstGetPutTest {
    private final String username = "ck_8da07afd3503a4242e95a2d5bad99cc2fb55c552";
    private final String password = "cs_e57e8cadc42ca42064c44239853dda9aa2e77474";
    private final String baseUrl = "http://localhost:10005/wp-json/wc/v3/";
    private final String productEndpoint = "products/";
    private final String productFuerta = "393";

    @Test
    public void firstGetTest() {
        Response response = given()
                //.port(10005)
                .auth()
                .oauth(username, password, "", "")

                .when()
                .get(baseUrl + productEndpoint + 975);

        // System.out.println(response.prettyPeek());
        // System.out.println((String) response.jsonPath().get("description"));
        System.out.println(response.getBody().prettyPrint());
        Assertions.assertEquals(200, response.statusCode());
    }

    @Test
    public void firstPostTest() {
        String body = "{\"name\": \"My first product\",\"stock_quantity\" : 15,\"images\" : [{\"id\" : \"63\"}]}";
        Response response = given()
                //.port(10005)
                .auth()
                .oauth(username, password, "", "")
                .contentType("application/json")
                .body(body)

                .when()
                .post(baseUrl + productEndpoint);

        System.out.println(response.getBody().prettyPrint());

        Assertions.assertEquals(201, response.getStatusCode());
        System.out.println(response.jsonPath().getString("id"));
    }
}
