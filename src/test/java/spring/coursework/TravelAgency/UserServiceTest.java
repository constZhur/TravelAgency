package spring.coursework.TravelAgency;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import spring.coursework.TravelAgency.models.User;
import spring.coursework.TravelAgency.services.UserService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserService userService;

    private int id;

    @BeforeEach
    public void setUp() {
        if (userService.findUserByEmail("test@test.com") == null){
            User user = new User("test@test.com", "testpassword");
            userService.save(user);
        }
        id = userService.findUserByEmail("test@test.com").getId();
    }

    @Test
    void testFindUserByEmail() {
        User user = userService.findUserByEmail("test@test.com");
        assertNotNull(user);
        assertEquals("test@test.com", user.getEmail());
    }

    @Test
    void testFindById() {
        User user = userService.findById(id);
        assertNotNull(user);
        assertEquals("test@test.com", user.getEmail());
    }

    @Test
    void testSave() {
        User user = new User();
        user.setEmail("newuser@test.com");
        user.setPassword("password");
        userService.save(user);
        User savedUser = userService.findUserByEmail("newuser@test.com");
        assertNotNull(savedUser);
        assertEquals("newuser@test.com", savedUser.getEmail());
        userService.deleteUserById(userService.findUserByEmail("newuser@test.com").getId());
    }

    @Test
    void testUpdateActivity() {
        User user = userService.findUserByEmail("test@test.com");
        userService.updateActivity(false, user);
        User updatedUser = userService.findUserByEmail("test@test.com");
        assertFalse(updatedUser.isActive());
    }

    @Test
    void testSetAdminStatus() {
        User user = userService.findUserByEmail("test@test.com");
        userService.setAdminStatus(user);
        User updatedUser = userService.findUserByEmail("test@test.com");
        assertEquals("ROLE_ADMIN", updatedUser.getRole());
    }

    @Test
    void testSetUserStatus() {
        User user = userService.findUserByEmail("test@test.com");
        userService.setUserStatus(user);
        User updatedUser = userService.findUserByEmail("test@test.com");
        assertEquals("ROLE_USER", updatedUser.getRole());
    }

    @Test
    void testDeleteCurrentUser() {
        List<User> users = userService.findAll();
        User user = userService.findUserByEmail("test@test.com");
        List<User> result = userService.deleteCurrentUser(users, user);
        assertFalse(result.contains(user));
    }

    @Test
    void testFindAll() {
        List<User> users = userService.findAll();
        assertNotNull(users);
        assertFalse(users.isEmpty());
    }

}
