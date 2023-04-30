package spring.coursework.TravelAgency.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.coursework.TravelAgency.models.Country;
import spring.coursework.TravelAgency.models.Tour;
import spring.coursework.TravelAgency.models.User;
import spring.coursework.TravelAgency.repositories.TourRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class TourService {
    private final TourRepository tourRepository;

    @Autowired
    public TourService(TourRepository tourRepository) {
        this.tourRepository = tourRepository;
    }

    public List<Tour> findAll() {
        return tourRepository.findAll();
    }

    public List<Tour> findByOwner(Country owner) {
        return tourRepository.findByOwner(owner);
    }

    public Tour findOne(Integer id){
        Optional<Tour> foundCountry = tourRepository.findById(id);
        return foundCountry.orElse(null);
    }


    public List<Tour> getToursByUser(User user) {
        return tourRepository.findByUsers(user);
    }

    @Transactional
    public void save(Tour tour){
            tourRepository.save(tour);
    }

    @Transactional
    public void update(Integer id, Tour updatedTour){
        updatedTour.setId(id);
        tourRepository.save(updatedTour);
    }

    @Transactional
    public void deleteById(Integer id){
        tourRepository.deleteById(id);
    }

    @Transactional
    public void deleteByName(String name){
        tourRepository.deleteTourByName(name);
    }
}
