package com.garant.dev.dao;

import java.util.List;

import com.garant.dev.model.ChatMessageRoom;
import com.garant.dev.model.UserChatMessage;

public interface UserChatMessageDao {
	
	public UserChatMessage getMessage(Long messageId);
	
    public void addMessage(UserChatMessage message);
    
    public List<UserChatMessage> getRecentMessages(int limit);
    
    public List<UserChatMessage> getAllMessages(String chatId);
    
    public List<UserChatMessage> getSenderMessages(int senderId);
    
    public List<UserChatMessage> getGroupedMessagesSender(int senderId);
    
    public List<UserChatMessage> getGroupedMessagesReciever(int recieverId);
    
    public void addChatMessageRoom(ChatMessageRoom chatMessageRoom);
	
	public ChatMessageRoom getUserChatMessageRoomByChatId(String chatId);
	
	public List<ChatMessageRoom> getAllUserChatRoomsBySenderId(int senderId);

}
