package com.garant.dev.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.garant.dev.model.Deal;
import com.garant.dev.model.User;
import com.garant.dev.service.CreditCardServiceImpl;
import com.garant.dev.service.DealService;
import com.garant.dev.service.UserService;
import com.garant.dev.util.CurrentUser;

/**
 * The controller class for transactions.
 */
@Controller
public class TransactionController {
	
    @Autowired
    private UserService userService;
    
	@Autowired
    private DealService dealService;
	
	@Autowired
    private CreditCardServiceImpl cardService;
	
	/**
     * This method returns cashin page.
     */
	@RequestMapping(value = { "/cashin" }, method = RequestMethod.GET)
	public String cashinGET(ModelMap model) {
		model.addAttribute("userName", CurrentUser.getCurrentUserName());
		return "cashin";
	}
	
	/**
     * This method returns cashout page.
     */
	@RequestMapping(value = { "/cashout" }, method = RequestMethod.GET)
	public String cashoutGET(ModelMap model) {
		model.addAttribute("userName", CurrentUser.getCurrentUserName());
		return "cashout";
	}
	
	/**
     * This method returns cashin page.
     */
	@RequestMapping(value = { "/cashin" }, method = RequestMethod.POST)
	public String cashinPOST(ModelMap model, @RequestParam(value = "card", defaultValue = "") String card,@RequestParam(value = "expMonth", defaultValue = "") String expMonth,
			@RequestParam(value = "expYear", defaultValue = "") String expYear, @RequestParam(value = "cardCvv", defaultValue = "") String cardCvv,
			@RequestParam(value = "cardHolder", defaultValue = "") String cardHolder) {
		model.addAttribute("userName", CurrentUser.getCurrentUserName());
		String infoData = cardService.buildInfoData(card, expMonth, expYear, cardCvv, cardHolder);
		cardService.getJSONFromApi(infoData);
		return "cashin";
	}
	
	/**
     * This method returns cashout page.
     */
	@RequestMapping(value = { "/cashout" }, method = RequestMethod.POST)
	public String cashoutPOST(ModelMap model) {
		model.addAttribute("userName", CurrentUser.getCurrentUserName());
		return "cashout";
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
	
	private User getCurrentUser() {
		String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
		return userService.findBySSO(currentUser);
	}

}
