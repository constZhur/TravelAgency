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

//    public List<Tour> findToursByUser(User user){
//        List<User> users = new ArrayList<>(Collections.singletonList(user));
//        return tourRepository.findAllToursByUsers(users);
//    }
}
