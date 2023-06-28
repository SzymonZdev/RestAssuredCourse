package OldTests;

import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;

public class CRUDtest extends BaseApiTest {

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
