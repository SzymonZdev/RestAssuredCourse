package OldTests;

import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;

public class jsonExercise extends BaseApiTest {
    @Test // Test to see if all products can be individually used in a GET request
    public void allProductsAreAvailable() {
        Response response = given()
                .param("per_page", 100)
                .when()
                .get(productEndpoint);

        List<Integer> listOfIds = response.jsonPath().get("id");
        listOfIds.forEach(e -> {
            Response newResponse = get(productEndpoint + e);
            Assertions.assertEquals(200, newResponse.getStatusCode());
            System.out.println("Tested id: " + e + ", named: " + newResponse.jsonPath().get("name"));
        });
    }
}
