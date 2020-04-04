package library.demo.controllers;

import library.demo.models.Publication;
import library.demo.models.User;
import library.demo.models.repositories.PublicationRepository;
import library.demo.models.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/publication")
@RestController
public class PublicationRestController {
    @Autowired
    PublicationRepository publicationRepository;

    @Autowired
    UserRepository userRepository;

    @GetMapping("/list")
    public ResponseEntity<List<Publication>> getPublications() {
        return new ResponseEntity<List<Publication>>(publicationRepository.findAll(Sort.by(Sort.Direction.DESC,
                "visits")), HttpStatus.OK);

    }

    @GetMapping("/by_user/{username}")
    public ResponseEntity<List<Publication>> getUserPublications(@PathVariable String username){
        User u = userRepository.findById(username).get();
        return new ResponseEntity<List<Publication>>(publicationRepository.findByUserName(u),
                HttpStatus.OK);
    }


}
