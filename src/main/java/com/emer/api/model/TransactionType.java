package com.emer.api.model;

public enum TransactionType {

	INVOICE("Invoice"), EXPENSE("Expense");

	private String transactionType;

	TransactionType(String type) {
		this.transactionType = type;
	}

	public String getTransactionType() {
		return transactionType;
	}
}
