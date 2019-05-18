package com.garant.dev.service;

import java.util.List;

import com.garant.dev.model.ChatMessage;

public interface ChatMessageService {
	
	public ChatMessage getMessage(Long messageId);

	public List<ChatMessage> getRecentMessages(int limit);

	public void addMessage(ChatMessage message);
	
	public List<ChatMessage> getAllMessages(String chatId);
	
	public List<ChatMessage> getSenderMessages(int senderId);
	
	public List<ChatMessage> getGroupedMessagesReciever(int recieverId);
	 
	public List<ChatMessage> getGroupedMessagesSender(int senderId);

	public StringBuilder fetchChatHistory(int limit);
	
	
}
