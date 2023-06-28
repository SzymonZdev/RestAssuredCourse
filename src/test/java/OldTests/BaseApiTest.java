package OldTests;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.oauth;

public class BaseApiTest {
    private static String username = "ck_8da07afd3503a4242e95a2d5bad99cc2fb55c552";
    private static String password = "cs_e57e8cadc42ca42064c44239853dda9aa2e77474";
    private static String basePath = "wp-json/wc/v3/";
    private static String baseUri = "http://localhost:10005/";
    protected static String productEndpoint = "products/";

    @BeforeAll
    public static void setUp() {
        RestAssured.baseURI = baseUri;
        RestAssured.basePath = basePath;
        RestAssured.authentication = oauth(username, password, "", "");
    }
}
