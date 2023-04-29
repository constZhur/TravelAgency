package spring.coursework.TravelAgency.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import spring.coursework.TravelAgency.models.Tour;
import spring.coursework.TravelAgency.models.User;
import spring.coursework.TravelAgency.services.TourService;
import spring.coursework.TravelAgency.services.UserService;

import java.util.List;

@RestController
@RequestMapping()
public class TourController {
    private final TourService tourService;
    private final UserService userService;

    @Autowired
    public TourController(TourService tourService, UserService userService) {
        this.tourService = tourService;
        this.userService = userService;
    }

//    @GetMapping("/my_tours")
//    public List <Tour> showUserTours(@RequestParam(value = "email", required = false) String email){
//        email += "@gmail.com";
//        User foundUser = userService.findUserByEmail(email);
//        return tourService.findToursByUser(foundUser);
//    }
}
