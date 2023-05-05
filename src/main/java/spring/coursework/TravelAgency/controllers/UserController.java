package spring.coursework.TravelAgency.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import spring.coursework.TravelAgency.models.Tour;
import spring.coursework.TravelAgency.models.User;
import spring.coursework.TravelAgency.services.TourService;
import spring.coursework.TravelAgency.services.UserService;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping()
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("admin/users_info")
    public String getAllUsers(Model model) {
        model.addAttribute("users", userService.deleteCurrentUser(userService.findAll(), userService.getCurrentUser()));
        return "/user/allUsers";
    }

    @PostMapping("/{id}/make_admin")
    public String makeAdmin(@PathVariable int id) {
        userService.setAdminStatus(userService.findById(id));
        return "redirect:/admin/users_info";
    }

    @PostMapping("/{id}/remove_admin")
    public String removeAdmin(@PathVariable int id) {
        userService.setUserStatus(userService.findById(id));
        return "redirect:/admin/users_info";
    }

    @PostMapping("/{id}/block")
    public String blockUser(@PathVariable int id) {
        userService.updateActivity(false, userService.findById(id));
        return "redirect:/admin/users_info";
    }
    @PostMapping("/{id}/unblock")
    public String unblockUser(@PathVariable int id) {
        userService.updateActivity(true, userService.findById(id));
        return "redirect:/admin/users_info";
    }
}
