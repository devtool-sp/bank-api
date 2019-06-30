package com.garant.dev.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.MediaType;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import com.garant.dev.model.UserDealMessage;
import com.garant.dev.model.UserChatMessage;
import com.garant.dev.model.Deal;
import com.garant.dev.model.User;
import com.garant.dev.service.DealService;
import com.garant.dev.service.MessageService;
import com.garant.dev.service.UserChatMessageService;
import com.garant.dev.service.UserService;
import com.garant.dev.util.ForbiddenException;
import com.garant.dev.util.ChatRoom;
import com.garant.dev.util.CurrentUser;

/**
 * Handles requests for chat messages.
 */
@Controller
@Scope("session")
public class CustomerServiceController {
	
	private int messagesPerPage = 50;

    private void setMessagePerPage(int limit) {
        this.messagesPerPage = limit;
    }
    
    private final String WELCOME_MESSAGE = "Напищите сообщение";

    @Autowired
    private UserService userService;

    @Autowired
    private MessageService messageService;
    
    @Autowired
    private UserChatMessageService simpleMessageService;
    
	@Autowired
    private DealService dealService;

    private static final Logger log = LoggerFactory.getLogger(CustomerServiceController.class);

    @RequestMapping(value = { "/chat" }, method = RequestMethod.GET)
    public String index(ModelMap modelMap, Map<String, Object> model) {
    		modelMap.addAttribute("userName", CurrentUser.getCurrentUserName());
        StringBuilder chatHistory = messageService.fetchChatHistory(messagesPerPage);
        User user = getCurrentUser();
        model.put("page", "home");
        model.put("authenticated", user != null ? true : false);
        model.put("user", user);
        model.put("chatHistory", chatHistory.toString());  
        return "chat";
    }

	/**
	 * This method returns chats page.
	 */
	@RequestMapping(value = { "/chats" }, method = RequestMethod.GET)
	public String prepare(ModelMap model) {
		model.addAttribute("userName", CurrentUser.getCurrentUserName());
		User user = getCurrentUser();
		if (user == null) {
			throw new ForbiddenException();
		}
		int id = user.getId();

		List<Deal> sellerdeals = dealService.findAllDealsSeller(id);
		List<UserDealMessage> sellerMessages = sellerdeals.get(0) != null
				? messageService.getMessages(sellerdeals.get(0).getId())
				: null;

		List<Deal> buyerdeals = dealService.findAllDealsBuyer(id);
		List<UserDealMessage> buyerMessages = buyerdeals.get(0) != null
				? messageService.getMessages(buyerdeals.get(0).getId())
				: null;
		String result = null;
		if (buyerMessages != null) {
			Iterator<UserDealMessage> itr = buyerMessages.iterator();
			while (itr.hasNext()) {
				UserDealMessage message = (UserDealMessage) itr.next();
				result += "<div class='chats-conversation-user-name'>" + message.getAuthor().getSsoId()
						+ "</div> <div class='chats-conversation-details'>" + "<div class='chats-conversation-message'>"
						+ message.getMessage() + "</div><div class='chats-conversation-message-time'>"
						+ message.getTimestamp() + "</div></div>";
			}
			model.put("chatHistory", result);
		} else if (sellerMessages != null) {
			Iterator<UserDealMessage> itr = sellerMessages.iterator();
			while (itr.hasNext()) {
				UserDealMessage message = (UserDealMessage) itr.next();
				result += "<div class='chats-conversation-user-name'>" + message.getAuthor().getSsoId()
						+ "</div> <div class='chats-conversation-details'>" + "<div class='chats-conversation-message'>"
						+ message.getMessage() + "</div><div class='chats-conversation-message-time'>"
						+ message.getTimestamp() + "</div></div>";
			}
			model.put("chatHistory", result);
		}
		return "chats";
	}

	/**
	 * This method returns chats page.
	 */
	@RequestMapping(value = { "/arbitration" }, method = RequestMethod.GET)
	public String arbitration(ModelMap model, HttpSession session, HttpServletRequest req) {
		model.addAttribute("userName", CurrentUser.getCurrentUserName());
		return "arbitration";
	}

	/**
	 * This method returns chats page.
	 */
	@RequestMapping(value = { "/customerservice" }, method = RequestMethod.GET)
	public String customerservice(ModelMap model, HttpSession session, HttpServletRequest req) {
		model.addAttribute("userName", CurrentUser.getCurrentUserName());
		return "customerservice";
	}

	/**
	 * This method returns deals section.
	 */
	@RequestMapping(value = { "/addcontact" }, method = RequestMethod.GET)
	public String newContact(ModelMap model) {
		model.addAttribute("userName", CurrentUser.getCurrentUserName());
		UserChatMessage simpleMessage = new UserChatMessage();
		model.addAttribute("simpleMessage", simpleMessage);
		return "addcontact";
	}

	/**
	 * This method returns deals section.
	 */
	@RequestMapping(value = { "/addcontact" }, method = RequestMethod.POST)
	public String addNewContact(ModelMap model, @ModelAttribute("simpleMessage") UserChatMessage simpleMessage,
			@RequestParam(value = "reciever", defaultValue = "") String reciever) {

		if (reciever != null && reciever.length() > 0) {
			User contractor = userService.findBySSO(reciever);
			simpleMessage.setReciever(contractor);
			simpleMessage.setSender(getCurrentUser());
			simpleMessage.setChatId(UUID.randomUUID().toString().replace("-", ""));
			simpleMessage.setMessage(WELCOME_MESSAGE);
			simpleMessageService.addMessage(simpleMessage);
		}

		return "addcontact";
	}

	@RequestMapping(value = "/loadChatHistoryAJAX.json", method = RequestMethod.GET)
	public @ResponseBody String loadChatHistory() {
		log.info("messagesPerPage = " + messagesPerPage);
		User user = getCurrentUser();
		if (user == null) {
			throw new ForbiddenException();
		}
		int id = user.getId();

		List<Deal> sellerdeals = dealService.findAllDealsSeller(id);
		List<UserDealMessage> sellerMessages = sellerdeals.get(0) != null
				? messageService.getMessages(sellerdeals.get(0).getId())
				: null;

		List<Deal> buyerdeals = dealService.findAllDealsBuyer(id);
		List<UserDealMessage> buyerMessages = buyerdeals.get(0) != null
				? messageService.getMessages(buyerdeals.get(0).getId())
				: null;
		String result = null;
		if (buyerMessages != null) {
			for (UserDealMessage message : buyerMessages) {

				result += "<div class='chats-conversation-user-name'>" + message.getAuthor().getSsoId()
						+ "</div> <div class='chats-conversation-details'>" + "<div class='chats-conversation-message'>"
						+ message.getMessage() + "</div><div class='chats-conversation-message-time'>"
						+ message.getTimestamp() + "</div></div>";
			}
			return result;
		} else if (sellerMessages != null) {
			for (UserDealMessage message : sellerMessages) {

				result += "<div class='chats-conversation-user-name'>" + message.getAuthor().getSsoId()
						+ "</div> <div class='chats-conversation-details'>" + "<div class='chats-conversation-message'>"
						+ message.getMessage() + "</div><div class='chats-conversation-message-time'>"
						+ message.getTimestamp() + "</div></div>";
			}
			return result;
		} else
			return "No data";
	}

	@RequestMapping(value = "/postMessageAJAX.json", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody UserDealMessage postChatMessage(@RequestBody UserDealMessage chatMessage) {
		User user = getCurrentUser();
		if (user == null) {
			throw new ForbiddenException();
		}
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		dateFormat.format(date);
		int id = user.getId();
		List<Deal> sellerdeals = dealService.findAllDealsSeller(id);
		Deal deal = sellerdeals.get(0);
		chatMessage.setAuthor(user);
		chatMessage.setChatDeal(deal);
		chatMessage.setTimestamp(date);
		// messageService.addMessage(chatMessage);
		// return new ChatRoom(ChatRoom.Status.SUCCESS);
		messageService.addMessage(chatMessage);
		return messageService.getMessage(chatMessage.getId());
		// return chatMessage;
	}

	@RequestMapping(value = "/setMessagesPerPagePropAJAX.json", method = RequestMethod.POST)
	public @ResponseBody String setMessagesPerPageProp(@RequestParam(value = "limit", required = false) int limit) {
		log.info("Setting messagesPerPage to " + limit);
		setMessagePerPage(limit);
		return new ChatRoom(ChatRoom.Status.SUCCESS).toString();
	}

	private User getCurrentUser() {
		String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
		return userService.findBySSO(currentUser);
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
