package spring.coursework.TravelAgency;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import spring.coursework.TravelAgency.models.User;
import spring.coursework.TravelAgency.repositories.UserRepository;
import spring.coursework.TravelAgency.services.UserDetailsServiceImpl;

import java.util.Optional;

@SpringBootTest
class UserDetailsServiceImplTest {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @MockBean
    private UserRepository userRepository;

    private User user;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setEmail("test@test.com");
        user.setPassword("password");
        user.setRole("ROLE_USER");
    }

    @Test
    void loadUserByUsername() {
        Mockito.when(userRepository.findUserByEmail(user.getEmail())).thenReturn(Optional.of(user));
        UserDetails userDetails = userDetailsService.loadUserByUsername(user.getEmail());
        Assertions.assertEquals(user.getEmail(), userDetails.getUsername());
    }

    @Test
    void loadUserByUsernameNotFound() {
        Mockito.when(userRepository.findUserByEmail(user.getEmail())).thenReturn(Optional.empty());
        Assertions.assertThrows(UsernameNotFoundException.class, () -> userDetailsService.loadUserByUsername(user.getEmail()));
    }

    @Test
    void isAdmin() {
        Mockito.when(userRepository.findByEmail(user.getEmail())).thenReturn(user);
        Assertions.assertFalse(userDetailsService.isAdmin(user.getEmail()));
        user.setRole("ROLE_ADMIN");
        Assertions.assertTrue(userDetailsService.isAdmin(user.getEmail()));
    }
}
