package com.emer.api.service;

import com.emer.api.model.DateSearch;
import com.emer.api.model.FinancialReport;
import com.emer.api.model.FinancialReportDiff;

public interface ReportingService {

	FinancialReport getSalesReport(DateSearch searchParams);
	
	public FinancialReport getExpensesReport(DateSearch searchParams);
	
	FinancialReportDiff getDifferenceReport(DateSearch searchParams);
}
