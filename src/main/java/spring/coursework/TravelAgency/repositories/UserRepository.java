package spring.coursework.TravelAgency.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.coursework.TravelAgency.models.Tour;
import spring.coursework.TravelAgency.models.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository <User, Integer> {
    Optional <User> findUserByEmail(String email);
}
