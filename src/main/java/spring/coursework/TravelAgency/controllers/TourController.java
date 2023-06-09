package spring.coursework.TravelAgency.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import spring.coursework.TravelAgency.models.Country;
import spring.coursework.TravelAgency.models.Tour;
import spring.coursework.TravelAgency.models.User;
import spring.coursework.TravelAgency.services.CountryService;
import spring.coursework.TravelAgency.services.TourService;
import spring.coursework.TravelAgency.services.UserService;

import java.io.IOException;


@Controller
@RequestMapping()
public class TourController {
    private final TourService tourService;
    private final CountryService countryService;
    private final UserService userService;

    @Autowired
    public TourController(TourService tourService, CountryService countryService, UserService userService) {
        this.tourService = tourService;
        this.countryService = countryService;
        this.userService = userService;
    }


    @GetMapping ("/my_tours")
    public String showUserTours(Model model) {
        User user = userService.getCurrentUser();
        model.addAttribute("tours", tourService.getToursByUser(user));
        //model.addAttribute("currentUser", userService.getCurrentUser());
        return "user/userTours";
    }

    @DeleteMapping("/my_tours")
    public String addTour(@RequestParam("tourId") Integer tourId) {
        tourService.removeTourFromUser(tourId, userService.getCurrentUser());
        if (userService.getCurrentUser().getRole().equals("ROLE_ADMIN")) return "redirect:/admin/index";
        return "redirect:/index";
    }

    @GetMapping("admin/tours_info")
    public String getAllTours(Model model) {
        model.addAttribute("tours", tourService.findAll());
        //model.addAttribute("currentUser", userService.getCurrentUser());
        return "tours/allTours";
    }


    @GetMapping("/admin/add_tour")
    public String createTour(@ModelAttribute("tour") Tour tour, Model model) {
        model.addAttribute("countries", countryService.findAll());
        model.addAttribute("model", model);
        //model.addAttribute("currentUser", userService.getCurrentUser());
        return "tours/addTour";
    }

    @PostMapping("/admin/add_tour")
    public String addTour(@ModelAttribute("tour") @Valid Tour tour, BindingResult bindingResult,
                          @RequestParam("countryId") Integer countryId, Model model){
        if (bindingResult.hasErrors()){
            createTour(tour, model);
            return "tours/addTour";
        }
        Country country = countryService.findById(countryId);
        tour.setOwner(country);
        tourService.save(tour);
        return "redirect:/admin/tours_info";
    }

    @DeleteMapping ("/admin/delete_tour/{id}")
    public String deleteTour(@PathVariable Integer id) {
        tourService.deleteById(id);
        return "redirect:/admin/tours_info";
    }
}
