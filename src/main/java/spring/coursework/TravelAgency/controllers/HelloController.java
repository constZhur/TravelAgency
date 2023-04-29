package spring.coursework.TravelAgency.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {
    @GetMapping("/home")
    public String sayHello(){
        return "Hello, World!";
    }
}
