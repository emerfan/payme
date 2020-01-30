package com.emer.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.emer.api.dao.ExpenseRepository;
import com.emer.api.dao.InvoiceRepository;
import com.emer.api.model.DateSearch;
import com.emer.api.model.FinancialReport;
import com.emer.api.model.FinancialReportDiff;

@Service
public class ReportingServiceImpl implements ReportingService {
	
	@Autowired
	private InvoiceRepository invoiceDao;
	
	@Autowired
	private ExpenseRepository expensesDao;
	
	@Override
	public FinancialReport getSalesReport(DateSearch searchParams){
		return this.invoiceDao.getSalesReport(searchParams.getDateFrom(), searchParams.getDateTo());
	}
	
	@Override
	public FinancialReport getExpensesReport(DateSearch searchParams){
		return this.expensesDao.getExpensesReport(searchParams.getDateFrom(), searchParams.getDateTo());
	}
	
	@Override
	public FinancialReportDiff getDifferenceReport(DateSearch searchParams) {
		FinancialReport sales = 
				this.invoiceDao.getSalesReport(searchParams.getDateFrom(), searchParams.getDateTo());
		FinancialReport expenses = 
				this.expensesDao.getExpensesReport(searchParams.getDateFrom(), searchParams.getDateTo());
		
		return new FinancialReportDiff(sales, expenses);
	}
}
