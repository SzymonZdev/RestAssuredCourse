package EndpointsConfig;

import POJOs.Product;

import static io.restassured.RestAssured.*;

public class ProductsEndpoint extends BasicEndpointConfiguration {
    private final String endpoint = "products/";

    public Product getProduct(int id) {
        lastResponse = get(endpoint + id);
        return lastResponse.as(Product.class);
    }
    public Product createProduct(Product product) {
        lastResponse =
                given()
                    .contentType(contentType)
                    .body(product)
                .when()
                    .post(endpoint);
        return lastResponse.as(Product.class);
    }
    public void deleteProduct(int id) {
        lastResponse = delete(endpoint + id);
        System.out.println("Deleted: " + id);
    }
}
