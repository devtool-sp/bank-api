
package com.garant.dev.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;

import com.garant.dev.model.Deal;
import com.garant.dev.model.User;
import com.garant.dev.service.DealService;
import com.garant.dev.service.UserService;
import com.garant.dev.util.CurrentUser;

/**
 * The controller class for main page.
 */
@Controller
public class MainPageController {
	
	@Autowired
    private DealService dealService;

	@Autowired
	MessageSource messageSource;
	
	@Autowired
	private UserService userService;
	
	/**
	 * This method returns main page.
	 */
	@RequestMapping(value = { "/main" }, method = RequestMethod.GET)
	public String prepare(ModelMap model) {		
		model.addAttribute("userName", CurrentUser.getCurrentUserName());
		return "main";
	}

	/**
	 * This method returns cabinet page.
	 */
	@RequestMapping(value = { "/cabinet" }, method = RequestMethod.GET)
	public String cabinet(ModelMap model) {
		model.addAttribute("userName", CurrentUser.getCurrentUserName());
		return "cabinet";
	}
	
	/**
	 * This method returns cabinet page.
	 */
	@RequestMapping(value = { "/head" }, method = RequestMethod.GET)
	public String headGet(ModelMap model) {
		return "head";
	}
	
	/**
	 * This method returns cabinet page.
	 */
	@RequestMapping(value = { "/head" }, method = RequestMethod.POST)
	public String headPost(ModelMap model) {		
		return "head";
	}

	/**
	 * This method provides functionality for main page.
	 */
	@RequestMapping(value = { "/main" }, method = RequestMethod.POST)
	public String welcomeMessage(@ModelAttribute("user") User user, HttpSession session, SessionStatus status,
			ModelMap model, @RequestParam(required = false) String logout) {
		model.addAttribute("userName", "fvevornborhbortbnrfd");
		if (logout != null) {
			session.invalidate();
			status.setComplete();
		}
		return "main";
	}

	/**
	 * This method handles logout requests.
	 */
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return "redirect:/login";
	}

	/**
	 * This method will provide current user to views
	 */
	public User getCurrentUser() {
		User currentUser = userService.findBySSO(CurrentUser.getCurrentUserName());
		return currentUser;
	}

	/**
	 * This method provides user deals with seller status to views
	 */
	@ModelAttribute("sellerdeals")
	public List<Deal> getCurrentUserSellerDeals() {
		User user = getCurrentUser();
		int id = user.getId();
		List<Deal> sellerdeals = dealService.findAllDealsSeller(id);
		return sellerdeals;
	}

	/**
	 * This method provides user deals with seller status to views
	 */
	@ModelAttribute("buyerdeals")
	public List<Deal> getCurrentUserBuyerDeals() {
		User user = getCurrentUser();
		int id = user.getId();
		List<Deal> buyerdeals = dealService.findAllDealsBuyer(id);
		return buyerdeals;
	}
	
	

}
