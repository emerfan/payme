package com.emer.api.service;

import com.emer.api.model.DateSearch;
import com.emer.api.model.FinancialReport;

public interface ReportingService {

	FinancialReport getSalesReport(DateSearch searchParams);

	FinancialReport getExpensesReport(DateSearch searchParams);
}