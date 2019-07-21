package myyuk.exam.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class LoginController {

    @GetMapping("/login")
    public String viewLogin(Model model, String error, String logout) {
        if ( error != null ) {
            model.addAttribute("errorMsg", "Your username and password are invalid.");
        }
        if ( logout != null ) {
            model.addAttribute("msg", "You have been logged out successfully");
        }
        return "home";
    }
}
