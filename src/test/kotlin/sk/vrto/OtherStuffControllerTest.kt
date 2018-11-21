package sk.vrto

import io.restassured.RestAssured
import io.restassured.RestAssured.get
import io.restassured.RestAssured.post
import org.hamcrest.CoreMatchers.containsString
import org.junit.Before
import org.junit.ClassRule
import org.junit.Rule
import org.junit.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.rules.SpringClassRule
import org.springframework.test.context.junit4.rules.SpringMethodRule

@SpringBootTest(
    classes = [Application::class],
    properties = ["server.port=9999"],
    webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT
)
class OtherStuffControllerTest {

    companion object {

        @ClassRule
        @JvmField
        val springClassRule = SpringClassRule()
    }

    @Rule
    @JvmField
    final val springMethodRule = SpringMethodRule()

    @Before
    fun setUp() {
        RestAssured.port = 9999
    }

    @Test
    fun `should get potato`() {
        get("/potato").then().statusCode(200).body(containsString("potato"))
    }

    @Test
    fun `should brew coffee`() {
        post("/coffee").then().statusCode(201)
    }
}