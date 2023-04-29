package spring.coursework.TravelAgency.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.coursework.TravelAgency.models.Tour;
import spring.coursework.TravelAgency.models.User;
import spring.coursework.TravelAgency.repositories.TourRepository;
import spring.coursework.TravelAgency.repositories.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findUserByEmail(String email){
        Optional<User> foundUser = userRepository.findUserByEmail(email);
        return foundUser.orElse(null);
    }
}
