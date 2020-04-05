package library.demo.controllers;

import library.demo.services.FileNamingService;
import library.demo.models.User;
import library.demo.models.repositories.UserRepository;
import library.demo.services.UploadedFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    UploadedFileService uploadedFileService;


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
    public ResponseEntity<String> changeProfilePicture(@RequestParam MultipartFile profilePicture,
                                               HttpServletRequest request, Authentication authentication) throws IOException {
        if(!profilePicture.getContentType().contains("image")){
            return ResponseEntity.badRequest().body("Not valid image format");
        }
        String uploadedName = uploadedFileService.uploadFile(profilePicture);
        if(uploadedName.equals("")){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error while " +
                    "uploading file");
        }
        User user = userRepository.findById(((UserDetails) authentication.getPrincipal()).getUsername()).get();
        user.setProfilePicture(uploadedName);
        userRepository.save(user);
        return ResponseEntity.ok().body("File uploaded");
    }
}
