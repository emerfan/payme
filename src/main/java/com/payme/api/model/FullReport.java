package com.payme.api.model;

public class FullReport {
	
	private DateSearch searchParams;
	
	private FinancialReport salesReport;
	
	private FinancialReport expensesReport;
	
	public FullReport() {	
	}

	public DateSearch getSearchParams() {
		return searchParams;
	}

	public void setSearchParams(DateSearch searchParams) {
		this.searchParams = searchParams;
	}

	public FinancialReport getSalesReport() {
		return salesReport;
	}

	public void setSalesReport(FinancialReport salesReport) {
		this.salesReport = salesReport;
	}

	public FinancialReport getExpensesReport() {
		return expensesReport;
	}

	public void setExpensesReport(FinancialReport expensesReport) {
		this.expensesReport = expensesReport;
	}	
	
}
