package library.demo.controllers;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginController {

    @RequestMapping("/other")
    public String otherPlace() {
        return "main_page";
    }

    @RequestMapping(value = "/login")
    public String login(@RequestParam(name = "error", defaultValue = "") String error,
                        Model model,Authentication authentication) {

        if(authentication !=null){
            return "redirect:other";
        }

        return "login_form";
    }


    @RequestMapping("/logout")
    public String logout(HttpServletRequest request) throws ServletException {
        request.logout();
        return "redirect:login";
    }
    @RequestMapping("/error_page")
    @ResponseBody
    public String error() {
        return "Error while login";

    }

    @RequestMapping("/index")
    @ResponseBody
    public String index() {
        return "Index";

    }

}
