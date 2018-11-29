package sk.vrto.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import sk.vrto.shared.DatabaseConfig;

@SpringBootApplication
@Import(DatabaseConfig.class)
public class UsersModuleApp {

    public static void main(String[] args) {
        SpringApplication.run(UsersModuleApp.class, args);
    }
}
