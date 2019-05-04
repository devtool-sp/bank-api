package com.garant.dev.validator;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.garant.dev.model.User;
import com.garant.dev.service.UserService;

/**
 * Validator for {@link net.proselyte.springsecurityapp.model.User} class,
 * implements {@link Validator} interface.
 *
 * @author Anna Likhachova
 * @version 1.0
 */

@Component
public class UserValidator implements Validator {

    @Autowired
    private UserService userService;

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        User user = (User) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "ssoId", "Required");
        if (user.getSsoId().length() < 5 || user.getSsoId().length() > 16) {
            errors.rejectValue("ssoId", "Size.userForm.username");
        }
        
        if (!user.getSsoId().matches("^[a-zA-Z]*$")) {
            errors.rejectValue("ssoId", "Size.userForm.username.regex");
        }
     

        if (userService.findBySSO(user.getSsoId()) != null) {
            errors.rejectValue("ssoId", "Duplicate.userForm.username");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "Required");
        if (user.getPassword().length() < 5 || user.getPassword().length() > 16) {
            errors.rejectValue("password", "Size.userForm.password");
        }

        if (!user.getConfirmPassword().equals(user.getPassword())) {
            errors.rejectValue("confirmPassword", "Different.userForm.password");
        }
    }
}
