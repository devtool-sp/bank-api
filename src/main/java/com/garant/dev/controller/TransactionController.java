package com.garant.dev.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.garant.dev.util.CurrentUser;

/**
 * The controller class for transactions.
 */
@Controller
public class TransactionController {
	
	/**
     * This method returns cashin page.
     */
	@RequestMapping(value = { "/cashin" }, method = RequestMethod.GET)
	public String cashin(ModelMap model) {
		model.addAttribute("userName", CurrentUser.getCurrentUserName());
		return "cashin";
	}
	
	/**
     * This method returns cashout page.
     */
	@RequestMapping(value = { "/cashout" }, method = RequestMethod.GET)
	public String cashout(ModelMap model) {
		model.addAttribute("userName", CurrentUser.getCurrentUserName());
		return "cashout";
	}

}
