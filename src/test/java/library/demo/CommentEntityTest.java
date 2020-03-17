package library.demo;

import library.demo.models.Publication;
import library.demo.models.User;
import library.demo.models.repositories.CommentRepository;
import library.demo.models.repositories.FileRepository;
import library.demo.models.repositories.PublicationRepository;
import library.demo.models.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CommentEntityTest {
    @Autowired
    PublicationRepository repository;


    @Autowired
    UserRepository userRepository;
    @Autowired
    CommentRepository commentRepository;

    @Test
    void contextLoads() {

        repository.deleteById(2);

    }

}
