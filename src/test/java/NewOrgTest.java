import EndpointsConfig.ProductsEndpoint;
import POJOs.Product;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class NewOrgTest {


    private static ProductsEndpoint productsEndpoint;
    private static List<Integer> idsToDelete = new ArrayList<>();


    @Test
    public void testGetOfProduct() {
        Product product = productsEndpoint.getProduct(393);
        Assertions.assertEquals(200, productsEndpoint.getLastStatusCode());
        Assertions.assertEquals("Fuerteventura - Sotavento", product.getName());
    }

    @Test
    public void testCreateNewProduct() {
        Product newProduct = new Product(0, "New Product to delete", "new-product-to-delete", "Lorep ipsum deletethisum");
        newProduct = productsEndpoint.createProduct(newProduct);
        Product finalNewProduct = newProduct;
        Assertions.assertAll(
                () -> Assertions.assertEquals(201, productsEndpoint.getLastStatusCode()),
                () -> Assertions.assertEquals("New Product to delete", finalNewProduct.getName())
        );
        idsToDelete.add(newProduct.getId());
    }

    @BeforeAll
    public static void setUp() {
        productsEndpoint = new ProductsEndpoint();
    }

    @AfterAll
    public static void cleanAfter() {
        for (int id : idsToDelete
             ) {
            productsEndpoint.deleteProduct(id);
        }
    }
}
