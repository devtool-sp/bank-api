package com.garant.dev.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name="CHAT_APP")
public class ChatMessageRoom {

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id" )
	@JsonIgnore 
    private Long id;

    @Column(name="timestamp", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @JsonIgnore  
    private Date timestamp;

    @ManyToOne
    @JoinColumn(name="sender_app_id")
    @JsonIgnore  
    private User userChatSender;
    
    @ManyToOne
    @JoinColumn(name="reciever_app_id")
    @JsonIgnore  
    private User userChatReciever;
    
    @Column(name="chat_app_id", nullable=false)
    @JsonIgnore  
    private String chatId;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((chatId == null) ? 0 : chatId.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((timestamp == null) ? 0 : timestamp.hashCode());
		result = prime * result + ((userChatReciever == null) ? 0 : userChatReciever.hashCode());
		result = prime * result + ((userChatSender == null) ? 0 : userChatSender.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ChatMessageRoom other = (ChatMessageRoom) obj;
		if (chatId == null) {
			if (other.chatId != null)
				return false;
		} else if (!chatId.equals(other.chatId))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (timestamp == null) {
			if (other.timestamp != null)
				return false;
		} else if (!timestamp.equals(other.timestamp))
			return false;
		if (userChatReciever == null) {
			if (other.userChatReciever != null)
				return false;
		} else if (!userChatReciever.equals(other.userChatReciever))
			return false;
		if (userChatSender == null) {
			if (other.userChatSender != null)
				return false;
		} else if (!userChatSender.equals(other.userChatSender))
			return false;
		return true;
	}
    
}
