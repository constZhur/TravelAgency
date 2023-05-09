package spring.coursework.TravelAgency.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.coursework.TravelAgency.models.Country;
import spring.coursework.TravelAgency.models.Tour;
import spring.coursework.TravelAgency.models.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface TourRepository extends JpaRepository<Tour, Integer> {
    List<Tour> findByOwner(Country country);
    List<Tour> findByUsers(User user);
    void deleteTourByName(String name);
    Optional<Tour> findByName(String name);
}
