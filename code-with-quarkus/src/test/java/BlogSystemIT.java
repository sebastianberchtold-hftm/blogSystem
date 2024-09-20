import blog.control.BlogServiceTest;
import io.quarkus.test.junit.QuarkusIntegrationTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

@QuarkusIntegrationTest
public class BlogSystemIT extends BlogServiceTest {
 /*   @Test
    public void testGetAllBlogs() {
        given()
                .when().get("/blogs")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("[0].title", is("Sample Title"))
                .body("[0].author", is("Sample Author"));
    }*/
}
