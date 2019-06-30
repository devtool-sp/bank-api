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
import com.garant.dev.model.ChatMessageRoom;
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
public class UserChatController {
    
    private final String WELCOME_MESSAGE = "Напищите сообщение";
    
    private static final Logger log = LoggerFactory.getLogger(UserChatController.class);

    @Autowired
    private UserService userService;
    
	@Autowired
    private DealService dealService;
	  
    @Autowired
    private UserChatMessageService userChatMessageService;

	/**
	 * This method returns chats page.
	 */
	@RequestMapping(value = { "/userchat" }, method = RequestMethod.GET)
	public String userchat(ModelMap model) {
		model.addAttribute("userName", CurrentUser.getCurrentUserName());
		User user = getCurrentUser();
		if (user == null) {
			throw new ForbiddenException();
		}
		int id = user.getId();	
		List<ChatMessageRoom> userChatMessageRoom = userChatMessageService.getAllUserChatRoomsBySenderId(id);
		model.addAttribute("userChatMessageRoom", userChatMessageRoom);
		return "userchat";
	}

	@RequestMapping(value = "/loadUserChatHistoryAJAX.json", method = RequestMethod.GET)
	public @ResponseBody String loadUserChatHistory(ModelMap model, HttpSession session) {
		List<UserChatMessage> allMessages = userChatMessageService.getAllMessages((String)session.getAttribute("chatId"));
		model.addAttribute("allMessages", allMessages);		
		return "";
	}
	
	@RequestMapping(value = "/postUserMessageAJAX.json", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody UserChatMessage postUserChatMessage(@RequestBody UserChatMessage chatMessage, ModelMap model, HttpSession session) {
		User user = getCurrentUser();
		if (user == null) {
			throw new ForbiddenException();
		}
		String chatId = (String)session.getAttribute("chatId");
		ChatMessageRoom chatMessageRoom = userChatMessageService.getUserChatMessageRoomByChatId(chatId);
		User reciever = chatMessageRoom.getUserChatReciever();
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		dateFormat.format(date);
		chatMessage.setSender(user);
		chatMessage.setTimestamp(date);
		chatMessage.setChatId(chatId);
		chatMessage.setReciever(reciever);
		userChatMessageService.addMessage(chatMessage);
		return userChatMessageService.getMessage(chatMessage.getId());
	}

	/**
	 * This method will redirect to the concrete chat
	 */
	@RequestMapping(value = { "/userchat-{id}" }, method = RequestMethod.GET)
	public String editUser(@PathVariable String id, ModelMap model, HttpSession session) {
		model.addAttribute("userName", CurrentUser.getCurrentUserName());
		List<UserChatMessage> allMessages = userChatMessageService.getAllMessages(id);
		User reciever = null;
		for(UserChatMessage firstMessage: allMessages) {
			if(firstMessage != null) {
				reciever = firstMessage.getReciever();
				break;
			}
		}
		
		//put to the model reciever
		model.addAttribute("reciever", reciever);
		model.addAttribute("chatId", id);
		session.setAttribute("chatId", id);
		model.addAttribute("allMessages", allMessages);
		return "userchat";
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
	
	/**
	 * This method provides recievers with seller status to views
	 */
	@ModelAttribute("userChatMessageRoom")
	public List<ChatMessageRoom> getCurrentUserMessagesRecievers(){
		User user = getCurrentUser();
		if (user == null) {
			throw new ForbiddenException();
		}
		int id = user.getId();	
		List<ChatMessageRoom> userChatMessageRoom = userChatMessageService.getAllUserChatRoomsBySenderId(id);
		return userChatMessageRoom;
	}
	
}
