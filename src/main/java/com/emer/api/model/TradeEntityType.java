package com.emer.api.model;

public enum TradeEntityType {

	DEBTOR("Debtor"), CREDITOR("Creditor");

	private String transactionType;

	TradeEntityType(String type) {
		this.transactionType = type;
	}

	public String getTransactionType() {
		return transactionType;
	}
}
