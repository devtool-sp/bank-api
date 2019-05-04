package com.garant.dev.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;
import com.garant.dev.service.UserProfileService;
import com.garant.dev.model.User;
import com.garant.dev.model.UserProfile;
import com.garant.dev.service.UserService;
import com.garant.dev.validator.UserValidator;


/**
 * The controller class for registration page.
 */
@Controller
public class RegistrationController {
	
	
	@Autowired
    private UserValidator userValidator;
	
	@Autowired
    private UserService userService;

	@Autowired
	UserProfileService userProfileService;
	     
	@Autowired
	MessageSource messageSource;
	
	/**
     * This method returns registration page.
     */
	@RequestMapping(value = { "/registration" }, method = RequestMethod.GET)
	public String registration(Model model) {
		User user = new User();
		model.addAttribute("user", user);
		return "registration";
	}
	
	/**
     * This is POST method for user registration.
     */
	@RequestMapping(value = { "/registration" }, method = RequestMethod.POST)
	public String registrationForm(@Valid @ModelAttribute("user") User user, BindingResult bindingResult,
            SessionStatus status, HttpSession session, HttpServletRequest req,
			 Model model) {
		
		userValidator.validate(user, bindingResult);

		if (bindingResult.hasErrors()) {
			return "registration";
		} else {
			session = req.getSession();
			session.setAttribute("loggedUser", user);
		}
		
        /*
        if(!userService.isUserSSOUnique(user.getId(), user.getSsoId())){
            FieldError ssoError =new FieldError("user","ssoId",messageSource.getMessage("non.unique.ssoId", new String[]{user.getSsoId()}, Locale.getDefault()));
            bindingResult.addError(ssoError);
            return "registration";
        }
        */
		
		/*
		User newUser = userService.findById(user.getId());
        List<UserProfile> profileList = new ArrayList<UserProfile>();
		UserProfile userProfile = new UserProfile();
		userProfile.setId(1);
		userProfile.setType("USER");
		profileList.add(userProfile);
		newUser.setUserProfiles(profileList);
		userService.saveUser(newUser);
		*/
		
		
        userService.saveUser(user);
        model.addAttribute("loggedUser", user);
        model.addAttribute("userName", user.getSsoId());
		return  "main";
	}
	
	 /**
     * This method will provide UserProfile list to views
     */
    @ModelAttribute("roles")
    public List<UserProfile> initializeProfiles() {
    	List<UserProfile> profileList = new ArrayList<UserProfile>();
    	UserProfile userProfile = new UserProfile();
    	userProfile.setId(1);
    	userProfile.setType("USER");
    	profileList.add(userProfile);
    	return	profileList;
    }
	
}
