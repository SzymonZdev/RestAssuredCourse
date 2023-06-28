package OldTests;

import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.when;

public class jsonTests extends BaseApiTest {

    @Test
    public void jsonMe() {
        Response response = when()
                .get(productEndpoint + "393");
        System.out.println(response.jsonPath().getString("categories[0].id"));
    }
}
