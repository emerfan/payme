package com.emer.api.model;

public class PeriodicalReport {
	
	public DateSearch timePeriod;
	
	public String totalExVatSales;
	
	public String vatSales;
	
	public String totalSales;
	
	public String totalExVatExpenses;
	
	public String vatExpenses;
	
	public String totalExpenses;
	
	public String vatDelta;
	
	public PeriodicalReport(){
	}

	public String getTotalExVatSales() {
		return totalExVatSales;
	}

	public void setTotalExVatSales(String totalExVatSales) {
		this.totalExVatSales = totalExVatSales;
	}

	public String getVatSales() {
		return vatSales;
	}

	public void setVatSales(String vatSales) {
		this.vatSales = vatSales;
	}

	public String getTotalSales() {
		return totalSales;
	}

	public void setTotalSales(String totalSales) {
		this.totalSales = totalSales;
	}

	public String getTotalExVatExpenses() {
		return totalExVatExpenses;
	}

	public void setTotalExVatExpenses(String totalExVatExpenses) {
		this.totalExVatExpenses = totalExVatExpenses;
	}

	public String getVatExpenses() {
		return vatExpenses;
	}

	public void setVatExpenses(String vatExpenses) {
		this.vatExpenses = vatExpenses;
	}

	public String getTotalExpenses() {
		return totalExpenses;
	}

	public void setTotalExpenses(String totalExpenses) {
		this.totalExpenses = totalExpenses;
	}

	public String getVatDelta() {
		return vatDelta;
	}

	public void setVatDelta(String vatDelta) {
		this.vatDelta = vatDelta;
	}
	
	public void setTimePeriod(DateSearch timePeriod) {
		this.timePeriod = timePeriod;
	}
	
	public DateSearch getTimePeriod(){
		return timePeriod;
	}
}
