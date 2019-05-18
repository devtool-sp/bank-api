package com.garant.dev.service;

import com.garant.dev.dao.MessageDao;
import com.garant.dev.dao.ChatMessageDao;
import com.garant.dev.model.ChatMessage;
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

@Service("simpleMessageService")
@Transactional(propagation= Propagation.SUPPORTS)
public class ChatMessageServiceImpl implements ChatMessageService{

    private static final Logger log = LoggerFactory.getLogger(ChatMessageServiceImpl.class);

    @Autowired
    private ChatMessageDao messageDao;

    public ChatMessage getMessage(Long messageId) {
        return messageDao.getMessage(messageId);
    }

    public List<ChatMessage> getRecentMessages(int limit) {
        List<ChatMessage> list = messageDao.getRecentMessages(limit);
        Collections.reverse(list);
        return list;
    }
    
    public List<ChatMessage> getAllMessages(String chatId){
        List<ChatMessage> list = messageDao.getAllMessages(chatId);
        Collections.reverse(list);
        return list;
    }
    
    public List<ChatMessage> getSenderMessages(int senderId){
        List<ChatMessage> list = messageDao.getSenderMessages(senderId);
        Collections.reverse(list);
        return list;
    }
    
    public List<ChatMessage> getGroupedMessagesSender(int senderId){
        List<ChatMessage> list = messageDao.getGroupedMessagesSender(senderId);
        Collections.reverse(list);
        return list;
    }
    
    public List<ChatMessage> getGroupedMessagesReciever(int recieverId){
        List<ChatMessage> list = messageDao.getGroupedMessagesReciever(recieverId);
        Collections.reverse(list);
        return list;
    }

    public void addMessage(ChatMessage message) {
        messageDao.addMessage(message);
    }

    public StringBuilder fetchChatHistory(int limit) {
        StringBuilder chatHistory = new StringBuilder();
        List<ChatMessage> messages = getRecentMessages(limit);

        for (ChatMessage m : messages ) {
            String added = getTimeDiff(m.getTimestamp());
            chatHistory.append(
                    String.format("%s <b>%s</b>: %s<br />",
                            added,
                            m.getSender(),
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

