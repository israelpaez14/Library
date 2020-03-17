package library.demo;

import library.demo.models.Like;
import library.demo.models.repositories.CommentRepository;
import library.demo.models.repositories.LikeRepository;
import library.demo.models.repositories.PublicationRepository;
import library.demo.models.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class LikeEntityTest {
    @Autowired
    LikeRepository likeRepository;

    @Test
    void contextLoads() {

    }

}
