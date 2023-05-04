package spring.coursework.TravelAgency.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import spring.coursework.TravelAgency.models.User;
import spring.coursework.TravelAgency.repositories.UserRepository;
import spring.coursework.TravelAgency.security.UserDetailsImpl;

import java.util.Objects;
import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findUserByEmail(username);

        if(user.isEmpty()) throw new UsernameNotFoundException("Пользователь с данным никнеймом не существует!");
        return new UserDetailsImpl(user.get());
    }

    public boolean isAdmin(String name){
        User user = userRepository.findByEmail(name);
        return Objects.equals(user.getRole(), "ROLE_ADMIN");
    }

}
