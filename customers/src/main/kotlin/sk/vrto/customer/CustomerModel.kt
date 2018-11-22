package sk.vrto.customer

import org.springframework.data.jpa.repository.JpaRepository
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table


@Entity
@Table(name = "customers")
data class Customer(
    @Id
    val id: Long = 0,
    val name: String = ""
)

interface CustomerRepository : JpaRepository<Customer, Long>