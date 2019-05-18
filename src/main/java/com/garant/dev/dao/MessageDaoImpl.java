package com.garant.dev.dao;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.garant.dev.model.DealMessage;

import static java.lang.Math.*;

import java.util.List;

@Repository("messageDao")
public class MessageDaoImpl implements MessageDao {

    private static final Logger log = LoggerFactory.getLogger(MessageDaoImpl.class);

    @Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional(readOnly=true)
    public DealMessage getMessage(Long messageId) {
        DealMessage message = (DealMessage) sessionFactory.getCurrentSession().get(DealMessage.class, messageId);
        if (log.isDebugEnabled()) {
            log.debug ("getMessage: " + message);
        }
        return message;
    }

    @Transactional(readOnly = false)
    public void addMessage(DealMessage message) {
        sessionFactory.getCurrentSession().save(message);
    }

    @Transactional(readOnly=true)
    public List<DealMessage> getRecentMessages(int limit) {
        Query query = sessionFactory.getCurrentSession().getNamedQuery("fetchRecentMessages");
        if (limit > 0) {
            query.setMaxResults(limit);
            List<DealMessage> list = query.list();
            return list.subList(0,
                    min(limit, list.size()));
        } else {
            List<DealMessage> list = query.list();
            return list;
        }
    }
    
    @Transactional(readOnly=true)
    public List<DealMessage> getMessages(int dealId){
    Query query = sessionFactory.getCurrentSession().createSQLQuery("select * from MESSAGES where deal_id="+dealId+" order by timestamp desc").addEntity(DealMessage.class);;
    	List<DealMessage> list = query.list();
    return list;
    	
    }
}
