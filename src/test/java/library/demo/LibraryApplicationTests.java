package library.demo;

import library.demo.models.User;
import library.demo.models.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
class LibraryApplicationTests {
    @Autowired
    UserRepository repository;

    @Autowired
    PasswordEncoder encoder;

    @Test
    void contextLoads() {

        String username = "israel";



        User a = repository.findById(username).get();

        a.setPassword(encoder.encode(a.getPassword()));
        repository.save(a);


    }

}
