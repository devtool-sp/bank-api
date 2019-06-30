package com.garant.dev.dao;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.garant.dev.model.UserDealMessage;
import com.garant.dev.model.UserChatMessage;
import com.garant.dev.model.ChatMessageRoom;
import com.garant.dev.model.User;

import static java.lang.Math.*;

import java.util.List;

@Repository("simpleMessageDao")
public class UserChatMessageDaoImpl implements UserChatMessageDao {

    private static final Logger log = LoggerFactory.getLogger(UserChatMessageDaoImpl.class);

    @Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional(readOnly=true)
    public UserChatMessage getMessage(Long messageId) {
    	UserChatMessage message = (UserChatMessage) sessionFactory.getCurrentSession().get(UserChatMessage.class, messageId);
        if (log.isDebugEnabled()) {
            log.debug ("getMessage: " + message);
        }
        return message;
    }

    @Transactional(readOnly = false)
    public void addMessage(UserChatMessage message) {
        sessionFactory.getCurrentSession().save(message);
    }

    @Transactional(readOnly=true)
    public List<UserChatMessage> getRecentMessages(int limit) {
        Query query = sessionFactory.getCurrentSession().getNamedQuery("fetchRecentMessages");
        if (limit > 0) {
            query.setMaxResults(limit);
            List<UserChatMessage> list = query.list();
            return list.subList(0,
                    min(limit, list.size()));
        } else {
            List<UserChatMessage> list = query.list();
            return list;
        }
    }
    
	@Transactional(readOnly = true)
	public List<UserChatMessage> getSenderMessages(int senderId) {
		Query query = sessionFactory.getCurrentSession()
				.createSQLQuery("select * from SIMPLE_MESSAGES where sender_id=" + senderId + " order by timestamp desc")
				.addEntity(UserChatMessage.class);
		List<UserChatMessage> list = query.list();
		return list;
	}

	@Transactional(readOnly = true)
	public List<UserChatMessage> getAllMessages(String chatId) {
		Query query = sessionFactory.getCurrentSession()
				.createSQLQuery("select * from SIMPLE_MESSAGES where chat_id=" + chatId + " order by timestamp desc")
				.addEntity(UserChatMessage.class);
		List<UserChatMessage> list = query.list();
		return list;
	}
	
	@Transactional(readOnly = true)
	public List<UserChatMessage> getGroupedMessagesSender(int senderId) {
	
		Query query = sessionFactory.getCurrentSession()
				.createSQLQuery("select * from SIMPLE_MESSAGES where sender_id=" + senderId + "")
				.addEntity(UserChatMessage.class);
		List<UserChatMessage> list = query.list();
		return list;
	}
	
	@Transactional(readOnly = true)
	public List<UserChatMessage> getGroupedMessagesReciever(int recieverId) {
		Query query = sessionFactory.getCurrentSession()
				.createSQLQuery("select * from SIMPLE_MESSAGES where reciever_id=" + recieverId + "")
				.addEntity(UserChatMessage.class);
		List<UserChatMessage> list = query.list();	
		return list;
	}
	
	@Transactional(readOnly = false)
	public void addChatMessageRoom(ChatMessageRoom chatMessageRoom) {
		sessionFactory.getCurrentSession().save(chatMessageRoom);
	}

	@Transactional(readOnly = true)
	public ChatMessageRoom getUserChatMessageRoomByChatId(String chatId) {
		Query query = sessionFactory.getCurrentSession()
				.createSQLQuery("select * from CHAT_APP where" + chatId + "")
				.addEntity(ChatMessageRoom.class);
		List<ChatMessageRoom> list = query.list();	
		ChatMessageRoom chatMessageRoom = list.get(0);
		/*
		ChatMessageRoom chatMessageRoom = (ChatMessageRoom) sessionFactory.getCurrentSession().get(ChatMessageRoom.class, chatId);
        if (log.isDebugEnabled()) {
            log.debug ("getMessage: " + chatMessageRoom);
        }
        */
        
        return chatMessageRoom;
	}
	
	@Transactional(readOnly = true)
	public List<ChatMessageRoom> getAllUserChatRoomsBySenderId(int senderId){
		Query query = sessionFactory.getCurrentSession()
				.createSQLQuery("select * from CHAT_APP where sender_app_id=" + senderId + " or reciever_app_id="+ senderId + "")
				.addEntity(ChatMessageRoom.class);
		List<ChatMessageRoom> list = query.list();	
		return list;		
	}
    
}
