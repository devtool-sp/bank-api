package com.garant.dev.model;

import javax.persistence.*;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Date;

@NamedQueries({
    @NamedQuery(
        name = "fetchRecentMessages",
        query = "from DealMessage m order by m.timestamp desc"
    )
})
@Entity
@Table(name="MESSAGES")
public class DealMessage implements Serializable{
	 
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id" )
	@JsonIgnore 
    private Long id;

    @Column(name="message", nullable=false)
    @JsonProperty("message") 
    private String message;

    @Column(name="timestamp", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @JsonIgnore  
    private Date timestamp;

    @ManyToOne
    @JoinColumn(name="author_id")
    @JsonIgnore  
    private User author;

    @ManyToOne
    @JoinColumn(name="deal_id")
    @JsonIgnore 
    private Deal chatDeal;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public User getAuthor() {
        return this.author;
    }

	public Deal getChatDeal() {
		return chatDeal;
	}

	public void setChatDeal(Deal chatDeal) {
		this.chatDeal = chatDeal;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((author == null) ? 0 : author.hashCode());
		result = prime * result + ((chatDeal == null) ? 0 : chatDeal.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((message == null) ? 0 : message.hashCode());
		result = prime * result + ((timestamp == null) ? 0 : timestamp.hashCode());
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
		DealMessage other = (DealMessage) obj;
		if (author == null) {
			if (other.author != null)
				return false;
		} else if (!author.equals(other.author))
			return false;
		if (chatDeal == null) {
			if (other.chatDeal != null)
				return false;
		} else if (!chatDeal.equals(other.chatDeal))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (message == null) {
			if (other.message != null)
				return false;
		} else if (!message.equals(other.message))
			return false;
		if (timestamp == null) {
			if (other.timestamp != null)
				return false;
		} else if (!timestamp.equals(other.timestamp))
			return false;
		return true;
	}
    
}
