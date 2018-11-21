package sk.vrto.customer

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class CustomerController {

    @GetMapping("/customer/{id}")
    fun getCustomer(@PathVariable("id") id: Long) = Customer(id, "Test Customer")

}

data class Customer(val id: Long, val name: String)