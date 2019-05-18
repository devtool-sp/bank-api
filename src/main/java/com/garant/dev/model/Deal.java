package com.garant.dev.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;
import com.garant.dev.model.User;

@Entity
@Table(name="USER_DEALS")
public class Deal implements Serializable{
	
	 @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	 private Integer id;
	 
	 @NotEmpty
	 @Column(name="DEAL_SUBJECT", nullable=false)
	 private String subject;
	     
	 @NotEmpty
	 @Column(name="QUANTITY", nullable=false)
	 private String quantity;
	 
	 @NotEmpty
	 @Column(name="COMPLECT", nullable=false)
	 private String complect;
	     
	 @NotEmpty
	 @Column(name="WEIGHT", nullable=false)
	 private String weight;
	 
	 @NotEmpty
	 @Column(name="ADDITIONALY", nullable=false)
	 private String additionaly;
	 
	 @NotNull
	 @Column(name="DEAL_SUM", nullable=false)
	 private Integer sum;
	 
	 @NotNull 
	 @Column(name="TERMS", nullable=false)
	 @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	 private Date terms;
	 
	 @ManyToOne
	 @JoinColumn(name = "buyer")
	 private User buyer;
	 
	 @ManyToOne
	 @JoinColumn(name = "seller")
	 private User seller;
	 
	 @OneToMany(mappedBy = "chatDeal")
	 private List<DealMessage> messageDeals;

	 @Transient
	 private List<String> status;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getComplect() {
		return complect;
	}

	public void setComplect(String complect) {
		this.complect = complect;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getAdditionaly() {
		return additionaly;
	}

	public void setAdditionaly(String additionaly) {
		this.additionaly = additionaly;
	}

	public Integer getSum() {
		return sum;
	}

	public void setSum(Integer sum) {
		this.sum = sum;
	}

	public Date getTerms() {
		return terms;
	}

	public void setTerms(Date terms) {
		this.terms = terms;
	}

	public User getBuyer() {
		return buyer;
	}

	public void setBuyer(User buyer) {
		this.buyer = buyer;
	}

	public User getSeller() {
		return seller;
	}

	public void setSeller(User seller) {
		this.seller = seller;
	}

	public List<DealMessage> getMessageDeals() {
		return messageDeals;
	}

	public void setMessageDeals(List<DealMessage> messageDeals) {
		this.messageDeals = messageDeals;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((additionaly == null) ? 0 : additionaly.hashCode());
		result = prime * result + ((buyer == null) ? 0 : buyer.hashCode());
		result = prime * result + ((complect == null) ? 0 : complect.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((messageDeals == null) ? 0 : messageDeals.hashCode());
		result = prime * result + ((quantity == null) ? 0 : quantity.hashCode());
		result = prime * result + ((seller == null) ? 0 : seller.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((subject == null) ? 0 : subject.hashCode());
		result = prime * result + ((sum == null) ? 0 : sum.hashCode());
		result = prime * result + ((terms == null) ? 0 : terms.hashCode());
		result = prime * result + ((weight == null) ? 0 : weight.hashCode());
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
		Deal other = (Deal) obj;
		if (additionaly == null) {
			if (other.additionaly != null)
				return false;
		} else if (!additionaly.equals(other.additionaly))
			return false;
		if (buyer == null) {
			if (other.buyer != null)
				return false;
		} else if (!buyer.equals(other.buyer))
			return false;
		if (complect == null) {
			if (other.complect != null)
				return false;
		} else if (!complect.equals(other.complect))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (messageDeals == null) {
			if (other.messageDeals != null)
				return false;
		} else if (!messageDeals.equals(other.messageDeals))
			return false;
		if (quantity == null) {
			if (other.quantity != null)
				return false;
		} else if (!quantity.equals(other.quantity))
			return false;
		if (seller == null) {
			if (other.seller != null)
				return false;
		} else if (!seller.equals(other.seller))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (subject == null) {
			if (other.subject != null)
				return false;
		} else if (!subject.equals(other.subject))
			return false;
		if (sum == null) {
			if (other.sum != null)
				return false;
		} else if (!sum.equals(other.sum))
			return false;
		if (terms == null) {
			if (other.terms != null)
				return false;
		} else if (!terms.equals(other.terms))
			return false;
		if (weight == null) {
			if (other.weight != null)
				return false;
		} else if (!weight.equals(other.weight))
			return false;
		return true;
	}
	 
}
