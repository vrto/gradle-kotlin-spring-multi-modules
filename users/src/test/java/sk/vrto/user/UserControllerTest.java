package sk.vrto.user;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import sk.vrto.shared.FunctionalTest;

import static io.restassured.RestAssured.get;
import static org.hamcrest.CoreMatchers.equalTo;

@SpringBootTest(
        classes = {UsersModuleApp.class},
        properties = {"server.port=9999"},
        webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT
)
public class UserControllerTest extends FunctionalTest {

    @Test
    public void shouldGetUser() {
        get("/user/6").then()
                .statusCode(200)
                .body("id", equalTo(6))
                .body("username", equalTo("Test username"));
    }

    @Test
    public void shouldReturnNotFound() {
        get("/user/-1").then().statusCode(404);
    }
}