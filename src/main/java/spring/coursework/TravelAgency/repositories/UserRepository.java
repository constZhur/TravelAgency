package spring.coursework.TravelAgency.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import spring.coursework.TravelAgency.models.Tour;
import spring.coursework.TravelAgency.models.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository <User, Integer> {
    Optional <User> findUserByEmail(String email);
    User findByEmail(String email);

    @Query(value = "DELETE FROM person_tour WHERE person_id = :personId AND tour_id = :tourId", nativeQuery = true)
    @Modifying
    void deleteUser(Integer personId, Integer tourId);

}
