package com.garant.dev.service;

import java.util.List;

import com.garant.dev.model.UserDealMessage;

public interface MessageService {
	
	public UserDealMessage getMessage(Long messageId);

	public List<UserDealMessage> getRecentMessages(int limit);
	
	public List<UserDealMessage> getMessages(int dealId);

	public void addMessage(UserDealMessage message);

	public StringBuilder fetchChatHistory(int limit);
}
