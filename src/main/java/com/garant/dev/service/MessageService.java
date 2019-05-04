package com.garant.dev.service;

import java.util.List;

import com.garant.dev.model.ChatMessage;

public interface MessageService {
	
	public ChatMessage getMessage(Long messageId);

	public List<ChatMessage> getRecentMessages(int limit);

	public void addMessage(ChatMessage message);

	public StringBuilder fetchChatHistory(int limit);
}
