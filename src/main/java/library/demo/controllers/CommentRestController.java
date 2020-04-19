package library.demo.controllers;

import library.demo.models.Comment;
import library.demo.models.repositories.CommentRepository;
import library.demo.models.repositories.PublicationRepository;
import library.demo.models.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/comments")
public class CommentRestController {
    @Autowired
    CommentRepository commentRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PublicationRepository publicationRepository;

    @RequestMapping(value = "/publish", method = RequestMethod.POST)
    public ResponseEntity<String> publishComment(@RequestParam int publication,
                                                 @RequestParam String content, Authentication authentication){

        Comment comment = new Comment();
        comment.setContent(content);
        comment.setLikes(0);
        comment.setUsername(userRepository.findById(((UserDetails)authentication.getPrincipal()).getUsername()).get());
        comment.setPublication(publicationRepository.findById(publication).get());

        System.out.println(comment);
        commentRepository.save(comment);
        return ResponseEntity.ok().body("Published");
    }
}
