package com.payme.api.service;

import com.payme.api.model.DateSearch;
import com.payme.api.model.FinancialReport;

public interface ReportingService {

	FinancialReport getSalesReport(DateSearch searchParams);

	FinancialReport getExpensesReport(DateSearch searchParams);
}