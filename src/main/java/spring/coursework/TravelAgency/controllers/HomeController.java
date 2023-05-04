package spring.coursework.TravelAgency.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import spring.coursework.TravelAgency.security.UserDetailsImpl;

@Controller
@RequestMapping
public class HomeController {
    @GetMapping("/home")
    public String home(){
        return "/home";
    }

    @GetMapping("/index")
    public String index(){
        return "user/index";
    }

}
