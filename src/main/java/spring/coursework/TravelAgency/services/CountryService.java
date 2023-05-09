package spring.coursework.TravelAgency.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import spring.coursework.TravelAgency.models.Country;
import spring.coursework.TravelAgency.models.CountryFlag;
import spring.coursework.TravelAgency.repositories.CountryRepository;

import java.awt.*;
import java.io.IOException;
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

    private CountryFlag toImageEntity(MultipartFile file) throws IOException {
        CountryFlag flag = new CountryFlag();
        flag.setName(file.getName());
        flag.setFileName(file.getOriginalFilename());
        flag.setContentType(file.getContentType());
        flag.setSize(file.getSize());
        flag.setBytes(file.getBytes());
        return flag;
    }

    @Transactional
    public void save(Country country, MultipartFile file) throws IOException {
        if (!countryRepository.existsByName(country.getName())){
            CountryFlag flag;
            if (file.getSize() != 0) {
                flag = toImageEntity(file);
                country.setFlag(flag);
            }
            countryRepository.save(country);
        }
    }

    @Transactional
    public void deleteById(Integer id){
        countryRepository.deleteById(id);
    }
}

