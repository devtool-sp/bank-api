package com.garant.dev.dao;

import java.util.List;

import com.garant.dev.model.DealMessage;

public interface MessageDao {
	
	public DealMessage getMessage(Long messageId);
	
    public void addMessage(DealMessage message);
    
    public List<DealMessage> getRecentMessages(int limit);
    
    public List<DealMessage> getMessages(int dealId);
}
