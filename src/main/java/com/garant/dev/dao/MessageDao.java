package com.garant.dev.dao;

import java.util.List;

import com.garant.dev.model.ChatMessage;

public interface MessageDao {
	public ChatMessage getMessage(Long messageId);
    public void addMessage(ChatMessage message);
    public List<ChatMessage> getRecentMessages(int limit);
}
