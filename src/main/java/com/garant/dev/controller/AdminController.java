package com.garant.dev.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.garant.dev.model.User;
import com.garant.dev.service.UserService;

/**
 * The controller class for admin page.
 */
@Controller
public class AdminController {
    
    @Autowired
    private UserService userService;	
	
    /**
     * This method returns the list of users.
     */
	@RequestMapping(value = { "/admin" }, method = RequestMethod.GET)
	public String login(ModelMap model) {	
		List<User> users = userService.findAllUsers();
        model.addAttribute("users", users);
        model.addAttribute("loggedinuser", getPrincipal());
		return "/admin";
	}

	 /**
     * This method will delete an user by it's SSOID value.
     */
	@RequestMapping(value = { "/admin" }, method = RequestMethod.POST)
	public String submit(@ModelAttribute("user") User user, @RequestParam(required = false) String username,
			@PathVariable String ssoId, BindingResult bindingResult, Model model) {
		
		user = userService.findBySSO(username);
		userService.deleteUserBySSO(ssoId);
		return "/admin";
				
	}
	
	/**
     * This method returns the principal[user-name] of logged-in user.
     */
    private String getPrincipal(){
        String userName = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
 
        if (principal instanceof UserDetails) {
            userName = ((UserDetails)principal).getUsername();
        } else {
            userName = principal.toString();
        }
        return userName;
    }    
 
}




