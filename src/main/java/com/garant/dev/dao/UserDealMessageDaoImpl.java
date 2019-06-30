package com.garant.dev.dao;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.garant.dev.model.UserDealMessage;

import static java.lang.Math.*;

import java.util.List;

@Repository("messageDao")
public class UserDealMessageDaoImpl implements UserDealMessageDao {

    private static final Logger log = LoggerFactory.getLogger(UserDealMessageDaoImpl.class);

    @Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional(readOnly=true)
    public UserDealMessage getMessage(Long messageId) {
        UserDealMessage message = (UserDealMessage) sessionFactory.getCurrentSession().get(UserDealMessage.class, messageId);
        if (log.isDebugEnabled()) {
            log.debug ("getMessage: " + message);
        }
        return message;
    }

    @Transactional(readOnly = false)
    public void addMessage(UserDealMessage message) {
        sessionFactory.getCurrentSession().save(message);
    }

    @Transactional(readOnly=true)
    public List<UserDealMessage> getRecentMessages(int limit) {
        Query query = sessionFactory.getCurrentSession().getNamedQuery("fetchRecentMessages");
        if (limit > 0) {
            query.setMaxResults(limit);
            List<UserDealMessage> list = query.list();
            return list.subList(0,
                    min(limit, list.size()));
        } else {
            List<UserDealMessage> list = query.list();
            return list;
        }
    }
    
    @Transactional(readOnly=true)
    public List<UserDealMessage> getMessages(int dealId){
    Query query = sessionFactory.getCurrentSession().createSQLQuery("select * from MESSAGES where deal_id="+dealId+" order by timestamp desc").addEntity(UserDealMessage.class);;
    	List<UserDealMessage> list = query.list();
    return list;
    	
    }
}
