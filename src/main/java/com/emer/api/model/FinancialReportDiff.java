package com.emer.api.model;

import java.math.BigDecimal;

public class FinancialReportDiff {
	
	private FinancialReport sales;
	
	private FinancialReport expenses;
	
	private BigDecimal diffTotal;
	
	private BigDecimal diffTotalExVat;
	
	private BigDecimal diffVat;
		
	
	public FinancialReportDiff(FinancialReport sales,
			FinancialReport expenses) {
		this.sales = sales;
		this.expenses = expenses;
		this.calculateDiff();
	}

	public FinancialReport getSales() {
		return sales;
	}

	public void setSales(FinancialReport sales) {
		this.sales = sales;
	}

	public FinancialReport getExpenses() {
		return expenses;
	}

	public void setExpenses(FinancialReport expenses) {
		this.expenses = expenses;
	}
	
	public BigDecimal getDiffTotal() {
		return diffTotal;
	}

	public void setDiffTotal(BigDecimal diffTotal) {
		this.diffTotal = diffTotal;
	}

	public BigDecimal getDiffTotalExVat() {
		return diffTotalExVat;
	}

	public void setDiffTotalExVat(BigDecimal diffTotalExVat) {
		this.diffTotalExVat = diffTotalExVat;
	}

	public BigDecimal getDiffVat() {
		return diffVat;
	}

	public void setDiffVat(BigDecimal diffVat) {
		this.diffVat = diffVat;
	}

	private void calculateDiff(){
		if (expenses.getSalesTotal() != null) {
			this.diffVat = sales.getSalesVat().subtract(expenses.getSalesVat());
			this.diffTotalExVat = sales.getSalesTotalExVat().subtract(expenses.getSalesTotalExVat());
			this.diffTotal = sales.getSalesTotal().subtract(expenses.getSalesTotal());
		} else {
			this.diffVat = this.diffTotalExVat = this.diffTotal = BigDecimal.ZERO;
		}
	}
}
