package spring.coursework.TravelAgency.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class HomeController {
    @GetMapping("/home")
    public String home(){
        return "/home";
    }

    @GetMapping("/index")
    public String userHomePage(){
        return "allUsers";
    }

    @GetMapping("admin/index")
    public String adminHomePage(){
        return "admin/index";
    }

}
