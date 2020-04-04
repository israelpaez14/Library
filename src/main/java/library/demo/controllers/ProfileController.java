package library.demo.controllers;

import library.demo.models.User;
import library.demo.models.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;


@RequestMapping("/profile")
@Controller(value = "profController")
public class ProfileController {
    @Autowired
    UserRepository userRepository;

    @RequestMapping("/view")
    public String showProfile(Authentication authentication, Model model) {
        User user = userRepository.findById(((UserDetails) authentication.getPrincipal()).getUsername()).get();
        model.addAttribute(user);
        model.addAttribute("authenticatedUser", authentication.getPrincipal());
        return "profile";

    }


    @RequestMapping("/view/{username}")
    public String showUserProfile(@PathVariable String username, Model model, Authentication authentication){
        Optional userOptional = userRepository.findById(username);
        if (!userOptional.isPresent()){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "entity not found"
            );
        }
        User user = (User) userOptional.get();
        model.addAttribute(user);
        model.addAttribute("authenticatedUser", authentication.getPrincipal());
        return "profile";
    }
}
