package sk.vrto

import org.junit.Test
import sk.vrto.customer.Customer


class OtherModuleStuffCompilesTest {

    @Test
    fun `should compile`() {
        Customer(1, "Misko") // Customer class lives in a different module
    }
}