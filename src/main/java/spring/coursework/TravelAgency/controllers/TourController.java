package spring.coursework.TravelAgency.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import spring.coursework.TravelAgency.models.Country;
import spring.coursework.TravelAgency.models.Tour;
import spring.coursework.TravelAgency.services.CountryService;
import spring.coursework.TravelAgency.services.TourService;


@Controller
@RequestMapping("/tours")
public class TourController {
    private final TourService tourService;
    private final CountryService countryService;

    @Autowired
    public TourController(TourService tourService, CountryService countryService) {
        this.tourService = tourService;
        this.countryService = countryService;
    }

    @GetMapping()
    public String getAllTours(Model model) {
        model.addAttribute("tours", tourService.findAll());
        return "tours/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("tour", tourService.findOne(id));
        return "tours/show";
    }

    @GetMapping("/new")
    public String createTour(@ModelAttribute("tour") Tour tour, Model model) {
        model.addAttribute("countries", countryService.findAll());
        model.addAttribute("model", model);
        return "/tours/new";
    }

    @PostMapping()
    public String addTour(@ModelAttribute("tour") @Valid Tour tour, BindingResult bindingResult,
                          @RequestParam("countryId") Integer countryId, Model model){
        if (bindingResult.hasErrors()){
            createTour(tour, model);
            return "/tours/new";
        }
        Country country = countryService.findById(countryId);
        tour.setOwner(country);
        tourService.save(tour);
        return "redirect:/tours";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Integer id){
        tourService.deleteById(id);
        return "redirect:/tours";
    }
}
