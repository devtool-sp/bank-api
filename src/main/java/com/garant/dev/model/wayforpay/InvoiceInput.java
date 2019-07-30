package com.garant.dev.model.wayforpay;

import java.io.Serializable;

public class InvoiceInput implements Serializable {

	private static final long serialVersionUID = 1L;
	private String transactionType;
	private String merchantAccount;
	private String merchantSecretKey;
	private String merchantDomainName;
	private String merchantSignature;
	private Integer apiVersion;
	private String orderReference;
	private Double amount;
	private String currency;
	private String[] productName;
	private Double[] productPrice;
	private Integer[] productCount;
	private Long orderDate;
	private String serviceUrl;

	public InvoiceInput() {

	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public String getMerchantAccount() {
		return merchantAccount;
	}

	public void setMerchantAccount(String merchantAccount) {
		this.merchantAccount = merchantAccount;
	}

	public String getMerchantSecretKey() {
		return merchantSecretKey;
	}

	public void setMerchantSecretKey(String merchantSecretKey) {
		this.merchantSecretKey = merchantSecretKey;
	}

	public String getMerchantDomainName() {
		return merchantDomainName;
	}

	public void setMerchantDomainName(String merchantDomainName) {
		this.merchantDomainName = merchantDomainName;
	}

	public String getMerchantSignature() {
		return merchantSignature;
	}

	public void setMerchantSignature(String merchantSignature) {
		this.merchantSignature = merchantSignature;
	}

	public Integer getApiVersion() {
		return apiVersion;
	}

	public void setApiVersion(Integer apiVersion) {
		this.apiVersion = apiVersion;
	}

	public String getOrderReference() {
		return orderReference;
	}

	public void setOrderReference(String orderReference) {
		this.orderReference = orderReference;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String[] getProductName() {
		return productName;
	}

	public void setProductName(String[] productName) {
		this.productName = productName;
	}

	public Double[] getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(Double[] productPrice) {
		this.productPrice = productPrice;
	}

	public Integer[] getProductCount() {
		return productCount;
	}

	public void setProductCount(Integer[] productCount) {
		this.productCount = productCount;
	}

	public Long getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Long orderDate) {
		this.orderDate = orderDate;
	}

	public String getServiceUrl() {
		return serviceUrl;
	}

	public void setServiceUrl(String serviceUrl) {
		this.serviceUrl = serviceUrl;
	}

}
