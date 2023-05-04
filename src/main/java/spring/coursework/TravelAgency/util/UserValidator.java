package spring.coursework.TravelAgency.util;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import spring.coursework.TravelAgency.models.User;
import spring.coursework.TravelAgency.services.UserService;

import java.util.Optional;

@Component
public class UserValidator implements Validator {
    private final UserService userService;

    @Autowired
    public UserValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;
        User findUser = userService.findUserByEmail(user.getEmail());
        if (findUser != null)
            errors.rejectValue("email", "", "Пользователь с такой электронной почтой уже существует");
    }
}
