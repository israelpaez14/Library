package library.demo.controllers;

import library.demo.models.Publication;
import library.demo.models.repositories.PublicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/publication")
@RestController
public class PublicationRestController {
    @Autowired
    PublicationRepository publicationRepository;

    @GetMapping("/list")
    public ResponseEntity<List<Publication>> getPublications() {
        return new ResponseEntity<List<Publication>>(publicationRepository.findAll(Sort.by(Sort.Direction.DESC,
                "visits")), HttpStatus.OK);

    }


}
