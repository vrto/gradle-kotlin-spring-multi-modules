package sk.vrto.customer

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication


// not a real application, but it'll pick up controllers from this module
@SpringBootApplication
class Application

fun main(args: Array<String>) {
    SpringApplication.run(Application::class.java, *args)
}