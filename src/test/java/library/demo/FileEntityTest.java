package library.demo;

import library.demo.models.repositories.FileRepository;
import library.demo.models.Publication;
import library.demo.models.repositories.PublicationRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class FileEntityTest {
    @Autowired
    PublicationRepository repository;

    @Autowired
    FileRepository fileRepository;

    @Test
    void contextLoads() {
        Publication p = repository.findById(1).get();
        repository.delete(p);

    }

}
