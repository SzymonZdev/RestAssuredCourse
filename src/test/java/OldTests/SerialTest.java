package OldTests;

import POJOs.Product;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.equalTo;

public class SerialTest extends BaseApiTest{

    @Test
    public void testDeserialization() {
        Product product = when()
                .get(productEndpoint + 393)
                .as(Product.class);
        System.out.println(product);
    }

    @Test
    public void testSerialization() {
        Product product = new Product(0, "Serialized object", "serialized-object", "Some very long and descriptive description");
        given()
                .contentType("application/json")
                .body(product)
                .post(productEndpoint)
                .then()
                .assertThat()
                .statusCode(201)
                .and()
                .assertThat()
                .body("name", equalTo("Serialized object"));
    }

    @Test
    public void serializationExerciseTest() {
        Product product = when()
                .get(productEndpoint + 989)
                .as(Product.class);
        product.setName("Serialized object new Name");
        product.setSlug("serialized-object-new-name");

        given()
                .contentType("application/json")
                .body(product)
                .put(productEndpoint + product.getId())

        .then()
                .assertThat()
                    .statusCode(200)
                .and()
                .assertThat()
                    .body("name", equalTo("Serialized object new Name"));
    }
}
