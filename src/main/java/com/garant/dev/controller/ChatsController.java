package com.garant.dev.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.garant.dev.model.ChatMessage;
import com.garant.dev.model.Deal;
import com.garant.dev.model.User;
import com.garant.dev.service.DealService;
import com.garant.dev.service.MessageService;
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

    @Autowired
    private UserService userService;

    @Autowired
    private MessageService messageService;
    
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
	public String prepare(ModelMap model, HttpSession session, HttpServletRequest req) {

		return "chats";
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

    @RequestMapping(value = "/loadChatHistoryAJAX.json", method=RequestMethod.GET)
    public @ResponseBody ChatRoom loadChatHistory() {
        log.info("messagesPerPage = " + messagesPerPage);
        StringBuilder chatHistory = messageService.fetchChatHistory(messagesPerPage);
        ChatRoom result = new ChatRoom(ChatRoom.Status.SUCCESS, "", chatHistory.toString());
        return result;
    }

    @RequestMapping(value = "/postMessageAJAX.json", method=RequestMethod.POST)
    public @ResponseBody ChatRoom postChatMessage(@RequestParam(value="data") String data) {
        User user = getCurrentUser();
        if (user == null) {
            throw new ForbiddenException();
        }
		int id = user.getId();
		List<Deal> sellerdeals = dealService.findAllDealsSeller(id);
		log.info("postMessageAJAX");
        Deal deal = sellerdeals.get(0);
        ChatMessage chatMessage = new ChatMessage(data, user, deal);
        messageService.addMessage(chatMessage);
        return new ChatRoom(ChatRoom.Status.SUCCESS);
    }

    @RequestMapping(value="/setMessagesPerPagePropAJAX.json",method=RequestMethod.POST)
    public @ResponseBody String setMessagesPerPageProp(@RequestParam(value = "limit", required = false) int limit) {
        log.info("Setting messagesPerPage to " + limit);
        setMessagePerPage(limit);
        return new ChatRoom(ChatRoom.Status.SUCCESS).toString();
    }

    private User getCurrentUser() {
        String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
        return userService.findBySSO(currentUser);
    }
}
