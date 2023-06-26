import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;

public class CRUDtest {
    private static String username = "ck_8da07afd3503a4242e95a2d5bad99cc2fb55c552";
    private static String password = "cs_e57e8cadc42ca42064c44239853dda9aa2e77474";
    private static String baseUrl = "http://localhost:10005/wp-json/wc/v3/";
    private static String basePath = "wp-json/wc/v3/";
    private static String baseUri = "http://localhost:10005/";
    private static String productEndpoint = "products/";

    @BeforeAll
    public static void setUp() {
        RestAssured.baseURI = baseUri;
        RestAssured.basePath = basePath;
        RestAssured.authentication = oauth(username, password, "", "");
    }

    @Test
    public void testGetAgain() {
        Response response =
                when()
                .get(productEndpoint + "393");

        Assertions.assertEquals(200, response.getStatusCode());
    }

    @Test
    public void testPostAgain() {
        Response response = given()
                .contentType("application/json")
                .body("{\"name\":\"Mauritius Test\"}")
                .when()
                .post(productEndpoint);

        Assertions.assertEquals(201, response.getStatusCode());
    }

    @Test
    public void testUpdate() {
        Response response = given()
                .contentType("application/json")
                .body("{\"name\":\"To be updated\"}")
                .when()
                .post(productEndpoint);

        Assertions.assertEquals(201, response.getStatusCode());
        String id = response.jsonPath().getString("id");

        response = given()
                .contentType("application/json")
                .body("{\"name\":\"Has been updated successfully\"}")
                .when()
                .put(productEndpoint + id);

        Assertions.assertEquals(200, response.getStatusCode());
    }

    @Test
    public void testDelete() {
        Response response = given()
                .contentType("application/json")
                .body("{\"name\":\"To be deleted\"}")
                .when()
                .post(productEndpoint);

        Assertions.assertEquals(201, response.getStatusCode());
        String id = response.jsonPath().getString("id");

        response = when()
                .delete(productEndpoint + id);

        Assertions.assertEquals(200, response.getStatusCode());
    }
}
