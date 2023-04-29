package spring.coursework.TravelAgency.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import spring.coursework.TravelAgency.models.Country;
import spring.coursework.TravelAgency.models.Tour;
import spring.coursework.TravelAgency.services.CountryService;
import spring.coursework.TravelAgency.services.TourService;

import java.util.List;

@RestController
@RequestMapping("/offers")
public class CountryController {

    private final CountryService countryService;
    private final TourService tourService;

    @Autowired
    public CountryController(CountryService countryService, TourService tourService) {
        this.countryService = countryService;
        this.tourService = tourService;
    }

    @GetMapping()
    public List<Country> index(){
        return countryService.findAll();
    }

    @GetMapping("/{name}")
    public Country showCountry(@PathVariable("name") String name){
        return countryService.findByName(name);
    }


    @GetMapping("/{name}/tours")
    public List<Tour> showTours(@PathVariable("name") String name){
        Country owner = countryService.findByName(name);
        return tourService.findByOwner(owner);
    }
}

