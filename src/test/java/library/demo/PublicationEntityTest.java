package library.demo;

import library.demo.models.Publication;
import library.demo.models.repositories.PublicationRepository;
import library.demo.models.User;
import library.demo.models.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class PublicationEntityTest {
    @Autowired
    PublicationRepository repository;
    @Autowired
    UserRepository userRepository;


    @Test
    void contextLoads() {


        User user = userRepository.findById("israel").get();

        userRepository.delete(user);


        List<User> users = userRepository.findAll();
        List<Publication> publications = repository.findAll();
        System.err.println("------------Existing users:-----------");

        users.forEach(userfor -> {
            System.err.println(userfor);
        });


        System.err.println("------Existing publications----------");

        publications.forEach(publication -> {
            System.err.println(publication);
        });


    }

}
