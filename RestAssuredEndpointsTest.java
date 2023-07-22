import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class ApiTest {

    @BeforeClass
    public static void setup() {
        RestAssured.baseURI = "https://reqres.in";
    }

    @Test
    public void testGetUser() {
        given()
            .when()
            .get("/api/users/2")
            .then()
            .statusCode(200)
            .contentType(ContentType.JSON)
            .body("data.id", equalTo(2))
            .body("data.email", equalTo("janet.weaver@reqres.in"))
            .body("data.first_name", equalTo("Janet"))
            .body("data.last_name", equalTo("Weaver"));
    }

    @Test
    public void testCreateUser() {
        String requestBody = "{\"name\": \"John\", \"job\": \"Developer\"}";

        given()
            .contentType(ContentType.JSON)
            .body(requestBody)
            .when()
            .post("/api/users")
            .then()
            .statusCode(201)
            .contentType(ContentType.JSON)
            .body("name", equalTo("John"))
            .body("job", equalTo("Developer"))
            .body("id", notNullValue())
            .body("createdAt", notNullValue());
    }

    @Test
    public void testUpdateUser() {
        String requestBody = "{\"name\": \"Jack\", \"job\": \"Engineer\"}";

        given()
            .contentType(ContentType.JSON)
            .body(requestBody)
            .when()
            .put("/api/users/2")
            .then()
            .statusCode(200)
            .contentType(ContentType.JSON)
            .body("name", equalTo("Jack"))
            .body("job", equalTo("Engineer"))
            .body("updatedAt", notNullValue());
    }

    @Test
    public void testDeleteUser() {
        given()
            .when()
            .delete("/api/users/2")
            .then()
            .statusCode(204);
    }
}
