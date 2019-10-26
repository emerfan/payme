package com.emer.api.model;

public class Report {
	
	private DateSearch searchParams;
	
	private SalesReport salesReport;
	
	private ExpensesReport expensesReport;
	
	public Report() {	
	}

	public DateSearch getSearchParams() {
		return searchParams;
	}

	public void setSearchParams(DateSearch searchParams) {
		this.searchParams = searchParams;
	}

	public SalesReport getSalesReport() {
		return salesReport;
	}

	public void setSalesReport(SalesReport salesReport) {
		this.salesReport = salesReport;
	}

	public ExpensesReport getExpensesReport() {
		return expensesReport;
	}

	public void setExpensesReport(ExpensesReport expensesReport) {
		this.expensesReport = expensesReport;
	}
}
