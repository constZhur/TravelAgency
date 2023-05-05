package spring.coursework.TravelAgency.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import spring.coursework.TravelAgency.models.Country;
import spring.coursework.TravelAgency.models.Tour;
import spring.coursework.TravelAgency.models.User;
import spring.coursework.TravelAgency.services.CountryService;
import spring.coursework.TravelAgency.services.TourService;
import spring.coursework.TravelAgency.services.UserService;

import java.util.List;

@Controller
@RequestMapping()
public class CountryController {

    private final CountryService countryService;
    private final TourService tourService;
    private final UserService userService;

    @Autowired
    public CountryController(CountryService countryService, TourService tourService, UserService userService) {
        this.countryService = countryService;
        this.tourService = tourService;
        this.userService = userService;
    }

    //работает
    @GetMapping("/countries")
    public String allCountries(Model model){
        model.addAttribute("countries", countryService.findAll());
        return "country/countries";
    }

//    @GetMapping("/{name}")
//    public Country showCountry(@PathVariable("name") String name){
//        return countryService.findByName(name);
//    }

    //работает
    @GetMapping("/{id}/tours")
    public String showTours(@PathVariable("id") Integer id, Model model){
        Country owner = countryService.findById(id);
        List<Tour> tours = tourService.deleteDuplicates(tourService.findByOwner(owner), userService.getCurrentUser().getTours());
        model.addAttribute("tours", tours);
        model.addAttribute("country", owner);
        return "country/tours";
    }

    @PostMapping("/{id}/tours")
    public String addTour(@PathVariable("id") Integer id, @RequestParam("tourId") Integer tourId) {
        User user = userService.getCurrentUser();
        tourService.addUserToTour(tourId, user);
        if (user.getRole().equals("ROLE_ADMIN")) return "redirect:/admin/index";
        return "redirect:/index";
    }

    @GetMapping("/admin/countries_info")
    public String countriesInfo(Model model){
        model.addAttribute("countries", countryService.findAll());
        return "country/allCountries";
    }
}

