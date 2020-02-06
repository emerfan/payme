package com.emer.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emer.api.dao.TransactionRepository;
import com.emer.api.model.DateSearch;
import com.emer.api.model.FinancialReport;
import com.emer.api.model.TransactionType;

@Service
public class ReportingServiceImpl implements ReportingService {

	@Autowired
	private TransactionRepository transactionDao;

	@Override
	public FinancialReport getSalesReport(DateSearch searchParams) {
		return this.transactionDao.getReport(searchParams.getDateFrom(), searchParams.getDateTo(),
				TransactionType.INVOICE);
	}

	@Override
	public FinancialReport getExpensesReport(DateSearch searchParams) {
		return this.transactionDao.getReport(searchParams.getDateFrom(), searchParams.getDateTo(),
				TransactionType.EXPENSE);
	}
}
