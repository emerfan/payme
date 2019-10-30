package com.emer.api.model;

public class Expense extends FinancialTransaction {

	public String creditorName;
	
	public Expense() {
	}

	public String getCreditorName() {
		return creditorName;
	}

	public void setCreditorName(String creditorName) {
		this.creditorName = creditorName;
	}	
}
