package library.demo.controllers;

import library.demo.models.Publication;
import library.demo.models.repositories.PublicationRepository;
import library.demo.models.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Controller
@RequestMapping("/publications")
public class PublicationContoller {
    @Autowired
    PublicationRepository publicationRepository;

    @RequestMapping("/visit/{id:\\d+}")
    public String visitPublication(@PathVariable int id, Model model) throws Exception {
        Optional optionalPublication = publicationRepository.findById(id);


        if(!optionalPublication.isPresent()){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "entity not found"
            );
        }else{
            Publication publication = (Publication) optionalPublication.get();
            publication.setVisits(publication.getVisits()+1);
            publicationRepository.save(publication);
            model.addAttribute("publication",optionalPublication.get());

        }

        return "visit";
    }
}
