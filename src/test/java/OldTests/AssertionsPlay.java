package OldTests;

import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import io.restassured.path.xml.exception.XmlPathException;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class AssertionsPlay extends BaseApiTest {

    @Test
    public void testGet() {
        given()

                .when()
                .get(productEndpoint + 393)

                .then()
                .assertThat()
                .statusCode(200)
                .and()
                .assertThat()
                .body("name", equalTo("Fuerteventura - Sotavento"));
    }

    @Test
    public void testGetWithAssertion() {
        ValidatableResponse validatableResponse = given()

                .when()
                .get(productEndpoint + 393)

                .then()
                .assertThat()
                .statusCode(200)
                .and()
                .assertThat()
                .body("name", equalTo("Fuerteventura - Sotavento"))
                .and()
                .assertThat()
                .body("related_ids.size()", equalTo(4));

        Assertions.assertThrows(XmlPathException.class, () -> validatableResponse.extract().xmlPath().get("test"));
    }
}
