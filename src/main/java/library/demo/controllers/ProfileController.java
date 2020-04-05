package library.demo.controllers;

import library.demo.configuration.FileNamingService;
import library.demo.models.User;
import library.demo.models.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Optional;


@RequestMapping("/profile")
@Controller(value = "profController")
public class ProfileController {
    @Autowired
    UserRepository userRepository;

    @Autowired
    FileNamingService fileNamingService;


    @RequestMapping("/view")
    public String showProfile(Authentication authentication, Model model) {
        User user = userRepository.findById(((UserDetails) authentication.getPrincipal()).getUsername()).get();
        model.addAttribute(user);
        model.addAttribute("authenticatedUser", authentication.getPrincipal());
        return "profile";

    }


    @RequestMapping("/view/{username}")
    public String showUserProfile(@PathVariable String username, Model model, Authentication authentication) {
        Optional userOptional = userRepository.findById(username);
        if (!userOptional.isPresent()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "entity not found"
            );
        }
        User user = (User) userOptional.get();
        model.addAttribute(user);
        model.addAttribute("authenticatedUser", authentication.getPrincipal());
        return "profile";
    }


    @RequestMapping(value = "/picture", method = RequestMethod.POST)
    public String changeProfilePicture(@RequestParam MultipartFile profilePicture,
                                       HttpServletRequest request, Authentication authentication) throws IOException {
        String uploadsFile = "";
        String realPath = "/tmp/uploads/";
        if(!profilePicture.getContentType().contains("image")){
            return "redirect:../other";
        }
        String savedFileName =
                fileNamingService.getValidName("."+profilePicture.getContentType().split("/")[1]);
        File transferFile = new File(realPath + "/" + savedFileName);
        User user = userRepository.findById(((UserDetails) authentication.getPrincipal()).getUsername()).get();
        user.setProfilePicture(transferFile.getName());
        userRepository.save(user);
        profilePicture.transferTo(transferFile);

        return "redirect:view";
    }
}
