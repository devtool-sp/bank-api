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

import com.garant.dev.model.DealMessage;
import com.garant.dev.model.ChatMessage;
import com.garant.dev.model.Deal;
import com.garant.dev.model.User;
import com.garant.dev.service.DealService;
import com.garant.dev.service.MessageService;
import com.garant.dev.service.ChatMessageService;
import com.garant.dev.service.UserService;
import com.garant.dev.util.ForbiddenException;
import com.garant.dev.util.ChatRoom;

/**
 * Handles requests for chat messages.
 */
@Controller
@Scope("session")
public class ChatsController {
	
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
    private ChatMessageService simpleMessageService;
    
	@Autowired
    private DealService dealService;

    private static final Logger log = LoggerFactory.getLogger(ChatsController.class);

    @RequestMapping(value = { "/chat" }, method = RequestMethod.GET)
    public String index(Map<String, Object> model) {
        log.info("messagesPerPage = " + messagesPerPage);
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
		User user = getCurrentUser();
		if (user == null) {
			throw new ForbiddenException();
		}
		int id = user.getId();

		List<Deal> sellerdeals = dealService.findAllDealsSeller(id);
		List<DealMessage> sellerMessages = sellerdeals.get(0) != null
				? messageService.getMessages(sellerdeals.get(0).getId())
				: null;

		List<Deal> buyerdeals = dealService.findAllDealsBuyer(id);
		List<DealMessage> buyerMessages = buyerdeals.get(0) != null
				? messageService.getMessages(buyerdeals.get(0).getId())
				: null;
		String result = null;
		if (buyerMessages != null) {
			Iterator<DealMessage> itr = buyerMessages.iterator();
			while (itr.hasNext()) {
				DealMessage message = (DealMessage) itr.next();
				result += "<div class='chats-conversation-user-name'>" + message.getAuthor().getSsoId()
						+ "</div> <div class='chats-conversation-details'>" + "<div class='chats-conversation-message'>"
						+ message.getMessage() + "</div><div class='chats-conversation-message-time'>"
						+ message.getTimestamp() + "</div></div>";
			}
			model.put("chatHistory", result);
		} else if (sellerMessages != null) {
			Iterator<DealMessage> itr = sellerMessages.iterator();
			while (itr.hasNext()) {
				DealMessage message = (DealMessage) itr.next();
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
	@RequestMapping(value = { "/userchat" }, method = RequestMethod.GET)
	public String userchat(ModelMap model) {
		User user = getCurrentUser();
		if (user == null) {
			throw new ForbiddenException();
		}
		int id = user.getId();

		List<ChatMessage> simpleMessageSender = simpleMessageService.getGroupedMessagesSender(Integer.valueOf(id));
		List<ChatMessage> simpleMessageReciever = simpleMessageService.getGroupedMessagesSender(Integer.valueOf(id));
		model.addAttribute("listOfSimpleMessagesSender", simpleMessageSender);
		model.addAttribute("listOfSimpleMessagesReciever", simpleMessageReciever);

		return "userchat";
	}

	/**
	 * This method returns chats page.
	 */
	@RequestMapping(value = { "/arbitration" }, method = RequestMethod.GET)
	public String arbitration(ModelMap model, HttpSession session, HttpServletRequest req) {

		return "arbitration";
	}

	/**
	 * This method returns chats page.
	 */
	@RequestMapping(value = { "/customerservice" }, method = RequestMethod.GET)
	public String customerservice(ModelMap model, HttpSession session, HttpServletRequest req) {

		return "customerservice";
	}

	/**
	 * This method returns deals section.
	 */
	@RequestMapping(value = { "/addcontact" }, method = RequestMethod.GET)
	public String newContact(ModelMap model) {
		ChatMessage simpleMessage = new ChatMessage();
		model.addAttribute("simpleMessage", simpleMessage);
		return "addcontact";
	}

	/**
	 * This method returns deals section.
	 */
	@RequestMapping(value = { "/addcontact" }, method = RequestMethod.POST)
	public String addNewContact(ModelMap model, @ModelAttribute("simpleMessage") ChatMessage simpleMessage,
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
		List<DealMessage> sellerMessages = sellerdeals.get(0) != null
				? messageService.getMessages(sellerdeals.get(0).getId())
				: null;

		List<Deal> buyerdeals = dealService.findAllDealsBuyer(id);
		List<DealMessage> buyerMessages = buyerdeals.get(0) != null
				? messageService.getMessages(buyerdeals.get(0).getId())
				: null;
		String result = null;
		if (buyerMessages != null) {
			for (DealMessage message : buyerMessages) {

				result += "<div class='chats-conversation-user-name'>" + message.getAuthor().getSsoId()
						+ "</div> <div class='chats-conversation-details'>" + "<div class='chats-conversation-message'>"
						+ message.getMessage() + "</div><div class='chats-conversation-message-time'>"
						+ message.getTimestamp() + "</div></div>";
			}
			return result;
		} else if (sellerMessages != null) {
			for (DealMessage message : sellerMessages) {

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
	public @ResponseBody DealMessage postChatMessage(@RequestBody DealMessage chatMessage) {
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

	@RequestMapping(value = "/loadUserChatHistoryAJAX.json", method = RequestMethod.GET)
	public @ResponseBody String loadUserChatHistory() {

		User user = getCurrentUser();
		if (user == null) {
			throw new ForbiddenException();
		}
		String result = null;
		int id = user.getId();
		List<ChatMessage> simpleMessageSender = simpleMessageService.getGroupedMessagesSender(id);
		for (ChatMessage message : simpleMessageSender) {
			result += "<div class='chats-contact-name'>" + message.getReciever().getSsoId()
					+ "</div> <div class='chats-conversation-details'>" + "<div class='chats-contact-status'>"
					+ "status" + "</div><div class='chats-contact-last-chat-time'>" + message.getTimestamp()
					+ "</div></div>";
		}
		return result;
	}

	@RequestMapping(value = "/postUserMessageAJAX.json", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ChatMessage postUserChatMessage(@RequestBody ChatMessage chatMessage,
			@RequestParam(value = "reciever", defaultValue = "") String reciever) {
		User user = getCurrentUser();
		if (user == null) {
			throw new ForbiddenException();
		}
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		dateFormat.format(date);
		if (reciever != null && reciever.length() > 0) {
			User recieverUser = userService.findBySSO(reciever);
			chatMessage.setReciever(recieverUser);
		}
		chatMessage.setSender(user);
		chatMessage.setTimestamp(date);
		simpleMessageService.addMessage(chatMessage);
		return simpleMessageService.getMessage(chatMessage.getId());
	}

	/**
	 * This method will redirect to the concrete chat
	 */
	@RequestMapping(value = { "/userchat-{id}" }, method = RequestMethod.GET)
	public String editUser(@PathVariable String id, ModelMap model) {
	
		List<ChatMessage> allMessages = simpleMessageService.getAllMessages(id);
		model.addAttribute("allMessages", allMessages);
		return "userchat";
	}

	private User getCurrentUser() {
		String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
		return userService.findBySSO(currentUser);
	}
}
