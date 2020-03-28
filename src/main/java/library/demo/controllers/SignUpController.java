package library.demo.controllers;


import library.demo.models.User;
import library.demo.models.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

@Controller
public class SignUpController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder encoder;

    @RequestMapping("/signup")
    public String signUp() {
        return "sign_up";
    }

    @RequestMapping(value = "/users", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> create(@Valid @RequestBody User username) {
        username.setPassword(encoder.encode(username.getPassword()));
        boolean validUsername = !userRepository.findById(username.getUserName()).isPresent();
        boolean validEmail =
                !userRepository.findAll().stream().filter(user -> user.getEmail().equals(username.getEmail())).findFirst().isPresent();
        ;
        if (!validUsername) {
            return new ResponseEntity<String>("Username already taken", HttpStatus.BAD_REQUEST);
        }

        if (!validEmail) {
            return new ResponseEntity<String>("Email already tanken", HttpStatus.BAD_REQUEST);

        }
        userRepository.save(username);

        return new ResponseEntity<String>(username.toString(), HttpStatus.OK);

    }


}
