package com.garant.dev.service;


import com.garant.dev.dao.UserChatMessageDao;
import com.garant.dev.model.ChatMessageRoom;
import com.garant.dev.model.UserChatMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service("userChatMessageService")
@Transactional(propagation= Propagation.SUPPORTS)
public class UserChatMessageServiceImpl implements UserChatMessageService{

    private static final Logger log = LoggerFactory.getLogger(UserChatMessageServiceImpl.class);

    @Autowired
    private UserChatMessageDao messageDao;

    public UserChatMessage getMessage(Long messageId) {
        return messageDao.getMessage(messageId);
    }

    public List<UserChatMessage> getRecentMessages(int limit) {
        List<UserChatMessage> list = messageDao.getRecentMessages(limit);
        Collections.reverse(list);
        return list;
    }
    
    public List<UserChatMessage> getAllMessages(String chatId){
        List<UserChatMessage> list = messageDao.getAllMessages(chatId);
        Collections.reverse(list);
        return list;
    }
    
    public List<UserChatMessage> getSenderMessages(int senderId){
        List<UserChatMessage> list = messageDao.getSenderMessages(senderId);
        Collections.reverse(list);
        return list;
    }
    
    public List<UserChatMessage> getGroupedMessagesSender(int senderId){
        List<UserChatMessage> list = messageDao.getGroupedMessagesSender(senderId);
        Collections.reverse(list);
        return list;
    }
    
    public List<UserChatMessage> getGroupedMessagesReciever(int recieverId){
        List<UserChatMessage> list = messageDao.getGroupedMessagesReciever(recieverId);
        Collections.reverse(list);
        return list;
    }

    public void addChatMessageRoom(ChatMessageRoom chatMessageRoom) {
        messageDao.addChatMessageRoom(chatMessageRoom);
    }
    
    public ChatMessageRoom getUserChatMessageRoomByChatId(String chatId) {
       return messageDao.getUserChatMessageRoomByChatId(chatId);
    }
    
    public List<ChatMessageRoom> getAllUserChatRoomsBySenderId(int senderId) {
        return messageDao.getAllUserChatRoomsBySenderId(senderId);
    }

	public void addMessage(UserChatMessage message) {
		messageDao.addMessage(message);
		
	}

}

