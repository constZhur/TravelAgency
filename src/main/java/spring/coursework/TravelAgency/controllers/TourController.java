package spring.coursework.TravelAgency.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import spring.coursework.TravelAgency.models.Tour;
import spring.coursework.TravelAgency.services.TourService;


@Controller
@RequestMapping("/tours")
public class TourController {
    private final TourService tourService;

    @Autowired
    public TourController(TourService tourService) {
        this.tourService = tourService;
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
    public String createTour(@ModelAttribute("tour") Tour tour) {
        return "/tours/new";
    }

    @PostMapping()
    public String addTour(@ModelAttribute("tour") @Valid Tour tour, BindingResult bindingResult){
        if (bindingResult.hasErrors())
            return "/tours/new";
        tourService.save(tour);
        return "redirect:/tours";
    }

    @DeleteMapping("delete/{id}")
    public String delete(@PathVariable("id") Integer id){
        tourService.deleteById(id);
        return "redirect:/tours";
    }
}
