package sk.vrto.user;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @GetMapping("/user/{userId}")
    public User getUserById(@PathVariable("userId") Long userId) {
        return new User(userId, "Test username");
    }
}
