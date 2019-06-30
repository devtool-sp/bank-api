package com.garant.dev.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.validator.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
 
@Getter
@Setter
@ToString
@Entity
@Table(name="APP_USER")
public class User implements Serializable{
 
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
 
    @NotEmpty
    @Column(name="SSO_ID", unique=true, nullable=false)
    private String ssoId;
     
    @NotEmpty
    @Column(name="PASSWORD", nullable=false)
    private String password;
 
    @Transient
    private String confirmPassword;
    
    @NotEmpty
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "APP_USER_USER_PROFILE", 
             joinColumns = { @JoinColumn(name = "USER_ID") }, 
             inverseJoinColumns = { @JoinColumn(name = "USER_PROFILE_ID") })
    private List<UserProfile> userProfiles; //= new HashSet<UserProfile>();
    
    @OneToMany(mappedBy = "buyer")
    private List<Deal> buyerdeals;

    @OneToMany(mappedBy = "seller")
    private List<Deal> sellerdeals;
    
    @OneToMany(mappedBy = "author")
    private List<UserDealMessage> chatMessages;
    
    @OneToMany(mappedBy = "sender")
    private List<UserChatMessage> senderMessages;
    
    @OneToMany(mappedBy = "reciever")
    private List<UserChatMessage> recieverMessages;
 
    @OneToMany(mappedBy = "userChatSender")
    private List<ChatMessageRoom> recieverChatMessageRoom;
    
    @OneToMany(mappedBy = "userChatReciever")
    private List<ChatMessageRoom> senderChatMessageRoom;
    
    @OneToMany(mappedBy = "cardHolderId")
    private List<CreditCard> creditCards;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((buyerdeals == null) ? 0 : buyerdeals.hashCode());
		result = prime * result + ((chatMessages == null) ? 0 : chatMessages.hashCode());
		result = prime * result + ((confirmPassword == null) ? 0 : confirmPassword.hashCode());
		result = prime * result + ((creditCards == null) ? 0 : creditCards.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((recieverChatMessageRoom == null) ? 0 : recieverChatMessageRoom.hashCode());
		result = prime * result + ((recieverMessages == null) ? 0 : recieverMessages.hashCode());
		result = prime * result + ((sellerdeals == null) ? 0 : sellerdeals.hashCode());
		result = prime * result + ((senderChatMessageRoom == null) ? 0 : senderChatMessageRoom.hashCode());
		result = prime * result + ((senderMessages == null) ? 0 : senderMessages.hashCode());
		result = prime * result + ((ssoId == null) ? 0 : ssoId.hashCode());
		result = prime * result + ((userProfiles == null) ? 0 : userProfiles.hashCode());
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
		User other = (User) obj;
		if (buyerdeals == null) {
			if (other.buyerdeals != null)
				return false;
		} else if (!buyerdeals.equals(other.buyerdeals))
			return false;
		if (chatMessages == null) {
			if (other.chatMessages != null)
				return false;
		} else if (!chatMessages.equals(other.chatMessages))
			return false;
		if (confirmPassword == null) {
			if (other.confirmPassword != null)
				return false;
		} else if (!confirmPassword.equals(other.confirmPassword))
			return false;
		if (creditCards == null) {
			if (other.creditCards != null)
				return false;
		} else if (!creditCards.equals(other.creditCards))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (recieverChatMessageRoom == null) {
			if (other.recieverChatMessageRoom != null)
				return false;
		} else if (!recieverChatMessageRoom.equals(other.recieverChatMessageRoom))
			return false;
		if (recieverMessages == null) {
			if (other.recieverMessages != null)
				return false;
		} else if (!recieverMessages.equals(other.recieverMessages))
			return false;
		if (sellerdeals == null) {
			if (other.sellerdeals != null)
				return false;
		} else if (!sellerdeals.equals(other.sellerdeals))
			return false;
		if (senderChatMessageRoom == null) {
			if (other.senderChatMessageRoom != null)
				return false;
		} else if (!senderChatMessageRoom.equals(other.senderChatMessageRoom))
			return false;
		if (senderMessages == null) {
			if (other.senderMessages != null)
				return false;
		} else if (!senderMessages.equals(other.senderMessages))
			return false;
		if (ssoId == null) {
			if (other.ssoId != null)
				return false;
		} else if (!ssoId.equals(other.ssoId))
			return false;
		if (userProfiles == null) {
			if (other.userProfiles != null)
				return false;
		} else if (!userProfiles.equals(other.userProfiles))
			return false;
		return true;
	}

}