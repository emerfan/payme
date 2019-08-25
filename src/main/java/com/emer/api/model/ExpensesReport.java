package com.emer.api.model;

import java.math.BigDecimal;

public class ExpensesReport {
	
	private BigDecimal expensesTotalExVat;
	
	private BigDecimal expensesVat;
	
	private BigDecimal expensesTotal;
	
	public ExpensesReport() {
	}

	public BigDecimal getExpensesTotalExVat() {
		return expensesTotalExVat;
	}

	public void setExpensesTotalExVat(BigDecimal expensesTotalExVat) {
		this.expensesTotalExVat = expensesTotalExVat;
	}

	public BigDecimal getExpensesVat() {
		return expensesVat;
	}

	public void setExpensesVat(BigDecimal expensesVat) {
		this.expensesVat = expensesVat;
	}

	public BigDecimal getExpensesTotal() {
		return expensesTotal;
	}

	public void setExpensesTotal(BigDecimal expensesTotal) {
		this.expensesTotal = expensesTotal;
	}
}
