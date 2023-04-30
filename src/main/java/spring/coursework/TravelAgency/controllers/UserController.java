package spring.coursework.TravelAgency.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import spring.coursework.TravelAgency.models.Tour;
import spring.coursework.TravelAgency.models.User;
import spring.coursework.TravelAgency.services.TourService;
import spring.coursework.TravelAgency.services.UserService;

import java.util.List;

@Controller
@RequestMapping()
public class UserController {

    private final UserService userService;
    private final TourService tourService;

    @Autowired
    public UserController(UserService userService, TourService tourService) {
        this.userService = userService;
        this.tourService = tourService;
    }

    @GetMapping("{userId}/my_tours")
    @ResponseBody
    public List<Tour> getToursByUser(@PathVariable Integer userId) {
        User user = userService.findById(userId);
        return tourService.getToursByUser(user);
    }
}
