package library.demo.controllers;


import library.demo.models.User;
import library.demo.models.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
    public User create(@Valid @RequestBody User username) {
        username.setPassword(encoder.encode(username.getPassword()));
        userRepository.save(username);
        return username;
    }


}
