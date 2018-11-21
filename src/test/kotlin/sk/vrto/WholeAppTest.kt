package sk.vrto

import io.restassured.RestAssured.get
import io.restassured.RestAssured.post
import org.hamcrest.CoreMatchers.containsString
import org.hamcrest.CoreMatchers.equalTo
import org.junit.Test
import org.springframework.boot.test.context.SpringBootTest
import sk.vrto.shared.FunctionalTest

@SpringBootTest(
    classes = [Application::class],
    properties = ["server.port=9999"],
    webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT
)
class WholeAppTest : FunctionalTest() {

    @Test
    fun `should get potato`() {
        get("/potato").then().statusCode(200).body(containsString("potato"))
    }

    @Test
    fun `should brew coffee`() {
        post("/coffee").then().statusCode(201)
    }

    // those tests is a mere proof that the 'main' app can see controllers from different modules
    // in reality it's better to test it diffeently, eg. only verify that the pat is known,
    // but keep the real tests in the 'customers' or 'users' module
    @Test
    fun `should get a customer`() {
        get("/customer/5").then()
            .statusCode(200)
            .body("id", equalTo(5))
            .body("name", equalTo("Test Customer"))
    }

    @Test
    fun `should get a user`() {
        get("/user/6").then()
            .statusCode(200)
            .body("id", equalTo(6))
            .body("username", equalTo("Test username"))
    }

}