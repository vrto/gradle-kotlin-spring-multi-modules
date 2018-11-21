package sk.vrto.shared

import io.restassured.RestAssured
import org.junit.Before
import org.junit.ClassRule
import org.junit.Rule
import org.springframework.test.context.junit4.rules.SpringClassRule
import org.springframework.test.context.junit4.rules.SpringMethodRule


open class FunctionalTest {

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

}