package com.garant.dev.dao;

import java.util.List;

import com.garant.dev.model.UserDealMessage;

public interface UserDealMessageDao {
	
	public UserDealMessage getMessage(Long messageId);
	
    public void addMessage(UserDealMessage message);
    
    public List<UserDealMessage> getRecentMessages(int limit);
    
    public List<UserDealMessage> getMessages(int dealId);
}
