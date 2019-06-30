package com.garant.dev.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;

import com.garant.dev.model.Deal;
import com.garant.dev.model.User;
import com.garant.dev.service.DealService;
import com.garant.dev.service.UserService;
import com.garant.dev.util.CurrentUser;
import com.garant.dev.validator.DealValidator;
import com.garant.dev.validator.UserValidator;

/**
 * The controller class for deals page.
 */
@Controller
public class DealsController {
	
	@Autowired
    private DealValidator dealValidator;
	
	@Autowired
    private DealService dealService;

	@Autowired
	MessageSource messageSource;
	
	@Autowired
	private UserService userService;

	/**
     * This method returns deals page.
     */
	@RequestMapping(value = { "/createdeal" }, method = RequestMethod.GET)
	public String prepare(ModelMap model) {
		model.addAttribute("userName", CurrentUser.getCurrentUserName());
		Deal deal = new Deal();
		model.addAttribute("deal", deal);
		User user = getCurrentUser();
		Integer id = user.getId();
		List<Deal> buyerdeals = dealService.findAllDealsBuyer(id);
		List<Deal> sellerdeals = dealService.findAllDealsSeller(id);	
		model.addAttribute("buyerdeals", buyerdeals);
		model.addAttribute("sellerdeals", sellerdeals);
		return "createdeal";
	}
	
	/**
     * This method returns all deals section.
     */
	@RequestMapping(value = { "/alldeals" }, method = RequestMethod.GET)
	public String showalldeals(ModelMap model, @ModelAttribute("buyerdeals") List<Deal> buyerdeals, @ModelAttribute("sellerdeals") List<Deal> sellerdeals) {
		model.addAttribute("userName", CurrentUser.getCurrentUserName());
		User user = getCurrentUser();
		int id = user.getId();
		buyerdeals = dealService.findAllDealsBuyer(id);
		sellerdeals = dealService.findAllDealsSeller(id);	
		return "alldeals";
	}
	
	/**
     * This method returns all deals section.
     */
	@RequestMapping(value = { "/alldeals" }, method = RequestMethod.POST)
	public String editdeals(ModelMap model,@RequestParam(value = "dealId", defaultValue = "") String dealId) {
		User user = getCurrentUser();
		int id = user.getId();
		Deal deal = dealService.findById(Integer.valueOf(dealId));
		int sellerId = deal.getSeller().getId();
		int buyerId = deal.getBuyer().getId();

		if (id == sellerId) {
			model.addAttribute("sellerdeal", deal);
			return "dealseller";
		}

		if (id == buyerId) {
			model.addAttribute("buyerdeal", deal);
			return "dealbuyer";
		}

		return "alldeals";
	}
	
	/**
     * This method returns deals section.
     */
	@RequestMapping(value = { "/deals" }, method = RequestMethod.GET)
	public String showalldeals(ModelMap model) {
		model.addAttribute("userName", CurrentUser.getCurrentUserName());
		return "deals";
	}
	
	/**
     * This method returns all deals section.
     */
	@RequestMapping(value = { "/dealseller-{id}" }, method = RequestMethod.GET)
	public String showSellerdeal(ModelMap model, @PathVariable String id) {
		model.addAttribute("userName", CurrentUser.getCurrentUserName());
		Deal deal = dealService.findById(Integer.valueOf(id));
		model.addAttribute("sellerDeal", deal);
		return "dealseller";
	}
	
	/**
     * This method returns all deals section.
     */
	@RequestMapping(value = { "/dealbuyer-{id}" }, method = RequestMethod.GET)
	public String showByuerdeal(ModelMap model, @PathVariable String id) {
		model.addAttribute("userName", CurrentUser.getCurrentUserName());
		Deal deal = dealService.findById(Integer.valueOf(id));
		model.addAttribute("buyerDeal", deal);
		return "dealbuyer";
	}
	
	/**
     * This method provides functionality for deals page.
     */
	@RequestMapping(value = { "/createdeal" },  method = RequestMethod.POST)
	public String welcomeMessage(@RequestParam(required = false) String logout, @Valid @ModelAttribute("deal") Deal deal, BindingResult bindingResult,
            SessionStatus status, HttpSession session, HttpServletRequest req, @RequestParam(value = "seller", defaultValue = "") String seller,  @RequestParam(value = "buyer", defaultValue = "") String buyer,
			 Model model) {
		User currentUser = getCurrentUser(); 
		
		if (seller != null && seller.length() > 0) {
			User contractor = userService.findBySSO(seller); 
			deal.setSeller(contractor);
			deal.setBuyer(currentUser);
       }
		
		if (buyer != null && buyer.length() > 0) {
			User contractor = userService.findBySSO(buyer); 
			deal.setBuyer(contractor);
			deal.setSeller(currentUser);
       }
		
		if (logout != null) {
			session.invalidate();
			status.setComplete();
		}
		
		dealValidator.validate(deal, bindingResult);

		if (bindingResult.hasErrors()) {
			// return "createdeal";
		} else {
			session = req.getSession();
			session.setAttribute("dealcreated", deal);
		}
		
		dealService.saveDeal(deal);
		model.addAttribute("dealcreated", deal);	
		return "main";
	}
	
	/**
     * This method will provide the medium to update an existing user.
     */
    @RequestMapping(value = { "/edit-deal-{id}" }, method = RequestMethod.GET)
    public String editUser(@PathVariable String id, ModelMap model) {
    	model.addAttribute("userName", CurrentUser.getCurrentUserName());
       Deal deal = dealService.findById(Integer.valueOf(id));
        model.addAttribute("deal", deal);
        model.addAttribute("edit", true);
        return "createdeal";
    }
     
    /**
     * This method will be called on form submission, handling POST request for
     * updating user in database. It also validates the user input
     */
    @RequestMapping(value = { "/edit-deal-{id}" }, method = RequestMethod.POST)
    public String updateUser(@Valid Deal deal, BindingResult result,
            ModelMap model, @PathVariable String id) {
 
        if (result.hasErrors()) {
            return "createdeal";
        }

        dealService.updateDeal(deal);
        return "deals";
    }
    
    /**
     * This method will delete an user by it's SSOID value.
     */
    @RequestMapping(value = { "/delete-deal-{id}" }, method = RequestMethod.GET)
    public String deleteUser(@PathVariable String id, ModelMap model) {
    	model.addAttribute("userName", CurrentUser.getCurrentUserName());
        dealService.deleteDealById(Integer.valueOf(id));
        return "redirect:/deals";
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
	
	/**
	 * This method provides current time
	 */
	@ModelAttribute("currentTime")
	public String getCurrentTime() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		return dateFormat.format(date);
	}
    	
}
