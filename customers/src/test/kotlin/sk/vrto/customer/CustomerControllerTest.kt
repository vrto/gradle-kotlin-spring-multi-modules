package sk.vrto.customer

import io.restassured.RestAssured.get
import org.hamcrest.CoreMatchers.equalTo
import org.junit.Test
import org.springframework.boot.test.context.SpringBootTest
import sk.vrto.shared.FunctionalTest

@SpringBootTest(
    classes = [Application::class],
    properties = ["server.port=9999"],
    webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT
)
class CustomerControllerTest : FunctionalTest() {

    @Test
    fun `should get a customer`() {
        get("/customer/5").then()
            .statusCode(200)
            .body("id", equalTo(5))
            .body("name", equalTo("Test Customer"))
    }

}
