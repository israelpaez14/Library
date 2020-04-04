package library.demo.controllers;

import library.demo.models.User;
import library.demo.models.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@RequestMapping("/profile")
@Controller(value = "profController")
public class ProfileController {
    @Autowired
    UserRepository userRepository;

    @RequestMapping("/view")
    public String showProfile(Authentication authentication, Model model) {
        User user = userRepository.findById(((UserDetails) authentication.getPrincipal()).getUsername()).get();
        model.addAttribute(user);
        return "profile";

    }
}
