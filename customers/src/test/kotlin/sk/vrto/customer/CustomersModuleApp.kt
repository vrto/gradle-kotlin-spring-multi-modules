package sk.vrto.customer

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Import
import sk.vrto.shared.DatabaseConfig


// not a real application, but it'll pick up controllers from this module
@SpringBootApplication
@Import(DatabaseConfig::class)
class CustomersModuleApp

fun main(args: Array<String>) {
    SpringApplication.run(CustomersModuleApp::class.java, *args)
}