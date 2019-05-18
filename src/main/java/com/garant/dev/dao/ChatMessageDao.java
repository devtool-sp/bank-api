package com.garant.dev.dao;

import java.util.List;

import com.garant.dev.model.ChatMessage;

public interface ChatMessageDao {
	
	public ChatMessage getMessage(Long messageId);
	
    public void addMessage(ChatMessage message);
    
    public List<ChatMessage> getRecentMessages(int limit);
    
    public List<ChatMessage> getAllMessages(String chatId);
    
    public List<ChatMessage> getSenderMessages(int senderId);
    
    public List<ChatMessage> getGroupedMessagesSender(int senderId);
    
    public List<ChatMessage> getGroupedMessagesReciever(int recieverId);
}
