package com.garant.dev.service;

import java.util.List;

import com.garant.dev.model.ChatMessageRoom;
import com.garant.dev.model.UserChatMessage;

public interface UserChatMessageService {
	
	public UserChatMessage getMessage(Long messageId);

	public List<UserChatMessage> getRecentMessages(int limit);

	public void addMessage(UserChatMessage message);
	
	public List<UserChatMessage> getAllMessages(String chatId);
	
	public List<UserChatMessage> getSenderMessages(int senderId);
	
	public List<UserChatMessage> getGroupedMessagesReciever(int recieverId);
	 
	public List<UserChatMessage> getGroupedMessagesSender(int senderId);
	
	public void addChatMessageRoom(ChatMessageRoom chatMessageRoom);
	
	public ChatMessageRoom getUserChatMessageRoomByChatId(String chatId);
	
	public List<ChatMessageRoom> getAllUserChatRoomsBySenderId(int senderId);
	
}
