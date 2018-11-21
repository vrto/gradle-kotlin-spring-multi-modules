package sk.vrto

import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatus.*
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
class OtherStuffController {

    @GetMapping("/potato")
    fun getPotato() = "potato"

    @PostMapping("/coffee")
    @ResponseStatus(CREATED)
    fun brewCoffee() {
        println("Coffee brewed!")
    }
}