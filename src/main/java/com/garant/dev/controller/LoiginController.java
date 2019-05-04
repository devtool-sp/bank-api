package com.garant.dev.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.garant.dev.model.User;
import com.garant.dev.service.UserService;


/**
 * The controller class for admin page.
 */
@Controller
public class LoiginController {
	
	/*
	@Autowired
   	AuthenticationTrustResolver authenticationTrustResolver;
    */
    
    @Autowired
    private UserService userService;
	
    /**
     * This method returns welcoming page.
     */
	@RequestMapping(value = { "/", "/index"}, method = RequestMethod.GET)
	public String prepareProduct(ModelMap model) {
		return "/index";
	}
		
	 /**
     * This method returns login page.
     */
	@RequestMapping(value = { "/login" }, method = RequestMethod.GET)
	public String login(ModelMap model) {	
		return "/login";
	}

	 /**
     * This method for user authorisation.
     */
	@RequestMapping(value = { "/login" }, method = RequestMethod.POST)
	public String submit(@ModelAttribute("user") User user, @RequestParam(required = false) String username, HttpSession session, HttpServletRequest req,
			 Model model) {
		
		user = userService.findBySSO(username);
		
		if (user == null) {			
			return "/login";
		} else {		
			model.addAttribute("loggedUser", user);
			model.addAttribute("userName", user.getSsoId());
			return "/main";
		}		
	}
	
}
/**
 * This method returns true if users is already authenticated [logged-in], else false.
	private boolean isCurrentAuthenticationAnonymous() {
    final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    return authenticationTrustResolver.isAnonymous(authentication);
	}
 */

