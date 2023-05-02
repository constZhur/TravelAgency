package spring.coursework.TravelAgency.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.coursework.TravelAgency.models.Country;
import spring.coursework.TravelAgency.repositories.CountryRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class CountryService {
    private final CountryRepository countryRepository;

    @Autowired
    public CountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    public List<Country> findAll() {
        return countryRepository.findAll();
    }

    public Country findById(Integer id){
        Optional<Country> foundCountry = countryRepository.findById(id);
        return foundCountry.orElse(null);
    }

    public Country findByName(String name){
        return countryRepository.findByName(name);
    }


//    @Transactional
//    public void save(Country item){
//        countryRepository.save(item);
//    }
//
//    @Transactional
//    public void update(Integer id, Country updatedCountry){
//        updatedCountry.setId(id);
//        countryRepository.save(updatedCountry);
//    }
//
//    @Transactional
//    public void delete(Integer id){
//        countryRepository.deleteById(id);
//    }
//
//
//    public List<Country> filterByName() {
//        return countryRepository.findAll(Sort.by("name"));
//    }
}

