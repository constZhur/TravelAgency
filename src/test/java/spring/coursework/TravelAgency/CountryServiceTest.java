package spring.coursework.TravelAgency;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;
import spring.coursework.TravelAgency.models.Country;
import spring.coursework.TravelAgency.models.User;
import spring.coursework.TravelAgency.repositories.CountryRepository;
import spring.coursework.TravelAgency.services.CountryService;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class CountryServiceTest {

    @Autowired
    private CountryService countryService;

    @Autowired
    private CountryRepository countryRepository;

    private int id;

    @BeforeEach
    void setUp() throws IOException {
        if (countryService.findByName("USSR") == null){
            Country country = new Country("USSR");
            MultipartFile file = new MockMultipartFile("/static/img/test.png", new byte[0]);
            countryService.save(country, file);
        }
        id = countryService.findByName("USSR").getId();
    }

    @Test
    void testFindAll() {
        List<Country> countries = countryService.findAll();
        assertNotNull(countries);
        assertFalse(countries.isEmpty());
    }

    @Test
    void testFindById() {
        Country country = countryService.findById(id);
        assertNotNull(country);
        assertEquals("USSR", country.getName());
    }

    @Test
    void testFindByName() {
        Country country = countryService.findByName("USSR");
        assertNotNull(country);
        assertEquals(id, country.getId());
    }

    @Test
    void testSave() throws IOException {
        Country country = new Country();
        country.setName("TestCountry");
        MultipartFile file = new MockMultipartFile("/static/img/test.png", new byte[0]);
        countryService.save(country, file);
        id = country.getId();
        Country savedCountry = countryRepository.findById(country.getId()).orElse(null);
        assertNotNull(savedCountry);
        assertEquals("TestCountry", savedCountry.getName());
    }

    @Test
    void testDeleteById() {
        countryService.deleteById(id);
        assertNull(countryRepository.findById(id).orElse(null));
    }
}
