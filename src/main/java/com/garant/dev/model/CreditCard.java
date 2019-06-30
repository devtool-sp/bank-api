package com.garant.dev.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name="CREDIT_CARD")
public class CreditCard implements Serializable{
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
	@Transient
	@JsonProperty("transactionType") 
	private String transactionType;
	@JsonProperty("merchantAccount") 
	private String merchantAccount;
	@Transient
	@JsonProperty("merchantAuthType") 
	private String merchantAuthType;
	@Transient
	@JsonProperty("merchantSignature") 
	private String merchantSignature;
	@Transient
	@JsonProperty("apiVersion") 
	private String apiVersion;
	@Transient
	@JsonProperty("orderReference") 
	private String orderReference;
	@Transient
	@JsonProperty("orderDate") 
	private String orderDate;
	@Column(name="amount", nullable=false)
	@JsonProperty("amount") 
	private Double amount;
	@Transient
	private String currency;
	@Column(name="card", nullable=false)
	@JsonProperty("card") 
	private String card;
	@Transient
	@JsonProperty("expMonth") 
	private String expMonth;
	@Transient
	@JsonProperty("expYear") 
	private String expYear;
	@Transient
	@JsonProperty("cardCvv") 
	private String cardCvv;
	@Transient
	@JsonProperty("cardHolder") 
	private String cardHolder;
	@Transient
	@JsonProperty("cardBeneficiary") 
	private String cardBeneficiary;
	@ManyToOne
	@JoinColumn(name="cardholder_id")
	@JsonIgnore  
	private User cardHolderId;
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((amount == null) ? 0 : amount.hashCode());
		result = prime * result + ((apiVersion == null) ? 0 : apiVersion.hashCode());
		result = prime * result + ((card == null) ? 0 : card.hashCode());
		result = prime * result + ((cardBeneficiary == null) ? 0 : cardBeneficiary.hashCode());
		result = prime * result + ((cardCvv == null) ? 0 : cardCvv.hashCode());
		result = prime * result + ((cardHolder == null) ? 0 : cardHolder.hashCode());
		result = prime * result + ((currency == null) ? 0 : currency.hashCode());
		result = prime * result + ((expMonth == null) ? 0 : expMonth.hashCode());
		result = prime * result + ((expYear == null) ? 0 : expYear.hashCode());
		result = prime * result + ((merchantAccount == null) ? 0 : merchantAccount.hashCode());
		result = prime * result + ((merchantAuthType == null) ? 0 : merchantAuthType.hashCode());
		result = prime * result + ((merchantSignature == null) ? 0 : merchantSignature.hashCode());
		result = prime * result + ((orderDate == null) ? 0 : orderDate.hashCode());
		result = prime * result + ((orderReference == null) ? 0 : orderReference.hashCode());
		result = prime * result + ((transactionType == null) ? 0 : transactionType.hashCode());
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
		CreditCard other = (CreditCard) obj;
		if (amount == null) {
			if (other.amount != null)
				return false;
		} else if (!amount.equals(other.amount))
			return false;
		if (apiVersion == null) {
			if (other.apiVersion != null)
				return false;
		} else if (!apiVersion.equals(other.apiVersion))
			return false;
		if (card == null) {
			if (other.card != null)
				return false;
		} else if (!card.equals(other.card))
			return false;
		if (cardBeneficiary == null) {
			if (other.cardBeneficiary != null)
				return false;
		} else if (!cardBeneficiary.equals(other.cardBeneficiary))
			return false;
		if (cardCvv == null) {
			if (other.cardCvv != null)
				return false;
		} else if (!cardCvv.equals(other.cardCvv))
			return false;
		if (cardHolder == null) {
			if (other.cardHolder != null)
				return false;
		} else if (!cardHolder.equals(other.cardHolder))
			return false;
		if (currency == null) {
			if (other.currency != null)
				return false;
		} else if (!currency.equals(other.currency))
			return false;
		if (expMonth == null) {
			if (other.expMonth != null)
				return false;
		} else if (!expMonth.equals(other.expMonth))
			return false;
		if (expYear == null) {
			if (other.expYear != null)
				return false;
		} else if (!expYear.equals(other.expYear))
			return false;
		if (merchantAccount == null) {
			if (other.merchantAccount != null)
				return false;
		} else if (!merchantAccount.equals(other.merchantAccount))
			return false;
		if (merchantAuthType == null) {
			if (other.merchantAuthType != null)
				return false;
		} else if (!merchantAuthType.equals(other.merchantAuthType))
			return false;
		if (merchantSignature == null) {
			if (other.merchantSignature != null)
				return false;
		} else if (!merchantSignature.equals(other.merchantSignature))
			return false;
		if (orderDate == null) {
			if (other.orderDate != null)
				return false;
		} else if (!orderDate.equals(other.orderDate))
			return false;
		if (orderReference == null) {
			if (other.orderReference != null)
				return false;
		} else if (!orderReference.equals(other.orderReference))
			return false;
		if (transactionType == null) {
			if (other.transactionType != null)
				return false;
		} else if (!transactionType.equals(other.transactionType))
			return false;
		return true;
	}

}
