package sk.vrto.shared

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.orm.jpa.JpaVendorAdapter
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.orm.jpa.vendor.Database
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter
import javax.persistence.EntityManagerFactory
import javax.sql.DataSource

@Configuration
class DatabaseConfig {

    @Bean
    fun dataSource(): EmbeddedDatabase = EmbeddedDatabaseBuilder()
        .setType(EmbeddedDatabaseType.H2)
        .addScript("database.sql")
        .setName("demo")
        .build()


    @Bean
    fun transactionManager(emf: EntityManagerFactory) = JpaTransactionManager(emf)

    @Bean
    fun jpaVendorAdapter(): JpaVendorAdapter {
        val jpaVendorAdapter = HibernateJpaVendorAdapter()
        jpaVendorAdapter.setDatabase(Database.H2)
        return jpaVendorAdapter
    }

    @Bean
    fun entityManagerFactory(dataSource: DataSource): LocalContainerEntityManagerFactoryBean {
        val lemfb = LocalContainerEntityManagerFactoryBean()
        lemfb.dataSource = dataSource
        lemfb.jpaVendorAdapter = jpaVendorAdapter()
        lemfb.setPackagesToScan("sk.vrto")
        return lemfb
    }
}