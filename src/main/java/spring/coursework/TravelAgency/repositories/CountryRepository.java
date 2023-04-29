package spring.coursework.TravelAgency.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.coursework.TravelAgency.models.Country;

import java.util.List;
import java.util.Optional;

@Repository
public interface CountryRepository extends JpaRepository <Country, Integer> {

    Country findByName(String name);
}
