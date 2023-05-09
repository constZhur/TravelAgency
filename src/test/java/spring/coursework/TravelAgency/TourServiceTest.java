package spring.coursework.TravelAgency;


import jakarta.annotation.PreDestroy;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import spring.coursework.TravelAgency.models.Country;
import spring.coursework.TravelAgency.models.Tour;
import spring.coursework.TravelAgency.models.User;
import spring.coursework.TravelAgency.services.CountryService;
import spring.coursework.TravelAgency.services.TourService;
import spring.coursework.TravelAgency.services.UserService;

import java.io.IOException;
import java.util.List;

@SpringBootTest
@Transactional
public class TourServiceTest {

    @Autowired
    private TourService tourService;

    private Tour tour;

    private int tourId, size;


    @BeforeEach
    public void setUp() throws IOException {
        if (tour == null){
            tour = new Tour("test tour", 100, "test description");
            tourService.save(tour);
        }
        tourId = tourService.findByName("test tour").getId();
        size = tourService.findAll().size();
    }

    @Test
    public void testFindOne() {
        Tour foundTour = tourService.findOne(tour.getId());
        Assertions.assertEquals(tour, foundTour);
    }


    @Test
    public void testSave() {
        Tour newTour = tour = new Tour("new test tour", 101, "new test description");
        tourService.save(newTour);
        Tour foundTour = tourService.findOne(newTour.getId());
        Assertions.assertEquals(newTour, foundTour);
        tourService.deleteById(newTour.getId());
    }

    @Test
    public void testUpdate() {
        Tour updatedTour = tour = new Tour("test tour", 102, "test description");
        tourService.update(tour.getId(), updatedTour);
        Tour foundTour = tourService.findOne(tour.getId());
        Assertions.assertEquals(updatedTour, foundTour);
        tourService.deleteById(updatedTour.getId());
    }

    @Test
    public void testDeleteById() {
        tourService.deleteById(tour.getId());
        List<Tour> tours = tourService.findAll();
        Assertions.assertEquals(size - 1, tours.size());
        Assertions.assertFalse(tours.contains(tour));
    }
}
