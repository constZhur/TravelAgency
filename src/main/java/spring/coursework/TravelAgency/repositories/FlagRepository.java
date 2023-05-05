package spring.coursework.TravelAgency.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.coursework.TravelAgency.models.CountryFlag;

public interface FlagRepository extends JpaRepository <CountryFlag, Integer> {
}
