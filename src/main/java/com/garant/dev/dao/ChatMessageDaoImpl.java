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

import com.garant.dev.model.DealMessage;
import com.garant.dev.model.ChatMessage;
import com.garant.dev.model.User;

import static java.lang.Math.*;

import java.util.List;

@Repository("simpleMessageDao")
public class ChatMessageDaoImpl implements ChatMessageDao {

    private static final Logger log = LoggerFactory.getLogger(ChatMessageDaoImpl.class);

    @Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional(readOnly=true)
    public ChatMessage getMessage(Long messageId) {
    	ChatMessage message = (ChatMessage) sessionFactory.getCurrentSession().get(ChatMessage.class, messageId);
        if (log.isDebugEnabled()) {
            log.debug ("getMessage: " + message);
        }
        return message;
    }

    @Transactional(readOnly = false)
    public void addMessage(ChatMessage message) {
        sessionFactory.getCurrentSession().save(message);
    }

    @Transactional(readOnly=true)
    public List<ChatMessage> getRecentMessages(int limit) {
        Query query = sessionFactory.getCurrentSession().getNamedQuery("fetchRecentMessages");
        if (limit > 0) {
            query.setMaxResults(limit);
            List<ChatMessage> list = query.list();
            return list.subList(0,
                    min(limit, list.size()));
        } else {
            List<ChatMessage> list = query.list();
            return list;
        }
    }
    
	@Transactional(readOnly = true)
	public List<ChatMessage> getSenderMessages(int senderId) {
		Query query = sessionFactory.getCurrentSession()
				.createSQLQuery("select * from SIMPLE_MESSAGES where sender_id=" + senderId + " order by timestamp desc")
				.addEntity(ChatMessage.class);
		List<ChatMessage> list = query.list();
		return list;
	}

	@Transactional(readOnly = true)
	public List<ChatMessage> getAllMessages(String chatId) {
		Query query = sessionFactory.getCurrentSession()
				.createSQLQuery("select * from SIMPLE_MESSAGES where chat_id=" + chatId + " order by timestamp desc")
				.addEntity(ChatMessage.class);
		List<ChatMessage> list = query.list();
		return list;
	}
	
	@Transactional(readOnly = true)
	public List<ChatMessage> getGroupedMessagesSender(int senderId) {
	
		Query query = sessionFactory.getCurrentSession()
				.createSQLQuery("select * from SIMPLE_MESSAGES where sender_id=" + senderId + "")
				.addEntity(ChatMessage.class);
		List<ChatMessage> list = query.list();
		return list;
	}
	
	@Transactional(readOnly = true)
	public List<ChatMessage> getGroupedMessagesReciever(int recieverId) {
		Query query = sessionFactory.getCurrentSession()
				.createSQLQuery("select * from SIMPLE_MESSAGES where reciever_id=" + recieverId + "")
				.addEntity(ChatMessage.class);
		List<ChatMessage> list = query.list();	
		return list;
	}
    
}
