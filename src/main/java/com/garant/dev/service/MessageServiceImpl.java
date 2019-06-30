package com.garant.dev.service;

import com.garant.dev.dao.UserDealMessageDao;
import com.garant.dev.model.UserDealMessage;
import org.joda.time.DateTime;
import org.joda.time.DurationFieldType;
import org.joda.time.Period;
import org.joda.time.PeriodType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.*;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service("messageService")
@Transactional(propagation= Propagation.SUPPORTS)
public class MessageServiceImpl implements MessageService{

    private static final Logger log = LoggerFactory.getLogger(MessageServiceImpl.class);

    @Autowired
    private UserDealMessageDao messageDao;

    public UserDealMessage getMessage(Long messageId) {
        return messageDao.getMessage(messageId);
    }

    public List<UserDealMessage> getRecentMessages(int limit) {
        List<UserDealMessage> list = messageDao.getRecentMessages(limit);
        Collections.reverse(list);
        return list;
    }
    
    public List<UserDealMessage> getMessages(int dealId){
        List<UserDealMessage> list = messageDao.getMessages(dealId);
        Collections.reverse(list);
        return list;
    }

    public void addMessage(UserDealMessage message) {
        messageDao.addMessage(message);
    }

    public StringBuilder fetchChatHistory(int limit) {
        StringBuilder chatHistory = new StringBuilder();
        List<UserDealMessage> messages = getRecentMessages(limit);

        for (UserDealMessage m : messages ) {
            String added = getTimeDiff(m.getTimestamp());
            chatHistory.append(
                    String.format("%s <b>%s</b>: %s<br />",
                            added,
                            m.getAuthor(),
                            m.getMessage())
            );
        }
        return chatHistory;
    }

    private String getTimeDiff(Date startTime) {
        DateTime now = new DateTime();
        PeriodType type = PeriodType.forFields(new DurationFieldType[] {
                DurationFieldType.minutes(),
                DurationFieldType.hours(),
                DurationFieldType.days(),
                DurationFieldType.months(),
                DurationFieldType.years()
        });

        Period period = new Period(new DateTime(startTime), now, type);
        return String.format("%d hours %d days %d months %d years ago",
                period.getHours(), period.getDays(), period.getMonths(), period.getYears());
    }
}

