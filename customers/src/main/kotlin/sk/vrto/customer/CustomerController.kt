package sk.vrto.customer

import org.springframework.http.ResponseEntity.notFound
import org.springframework.http.ResponseEntity.ok
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class CustomerController(val customerRepository: CustomerRepository) {

    @GetMapping("/customer/{id}")
    fun getCustomer(@PathVariable("id") id: Long) = customerRepository.findById(id)
        .map { ok(it) }
        .orElseGet { notFound().build() }
}
