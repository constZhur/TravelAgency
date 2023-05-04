package spring.coursework.TravelAgency.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.coursework.TravelAgency.models.User;
import spring.coursework.TravelAgency.repositories.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User findUserByEmail(String email){
        Optional<User> foundUser = userRepository.findUserByEmail(email);
        return foundUser.orElse(null);
    }

    public User findById(Integer id){
        Optional<User> foundCountry = userRepository.findById(id);
        return foundCountry.orElse(null);
    }

    @Transactional
    public void save(User user){
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setRole("ROLE_USER");
        user.setPassword(encodedPassword);
        userRepository.save(user);
    }

    @Transactional
    public void update(Integer id, User updatedUser){
        updatedUser.setId(id);
        userRepository.save(updatedUser);
    }

    @Transactional
    public void setAdmin(User updatedUser){
        updatedUser.setRole("ROLE_ADMIN");
        userRepository.save(updatedUser);
    }

    @Transactional
    public void deleteById(Integer id){
        userRepository.deleteById(id);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }
}
