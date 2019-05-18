package com.garant.dev.service;

import java.util.List;

import com.garant.dev.model.DealMessage;

public interface MessageService {
	
	public DealMessage getMessage(Long messageId);

	public List<DealMessage> getRecentMessages(int limit);
	
	public List<DealMessage> getMessages(int dealId);

	public void addMessage(DealMessage message);

	public StringBuilder fetchChatHistory(int limit);
}
