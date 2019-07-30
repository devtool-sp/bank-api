package com.garant.dev.model.wayforpay;

public enum TransactionType {

	CREATE_INVOICE("CREATE_INVOICE");

	private String transactionType;

	TransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public String transactionType() {
		return transactionType;
	}
}
