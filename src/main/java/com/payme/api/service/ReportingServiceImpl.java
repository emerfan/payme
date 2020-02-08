package com.payme.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.payme.api.dao.TransactionRepository;
import com.payme.api.model.DateSearch;
import com.payme.api.model.FinancialReport;
import com.payme.api.model.TransactionType;

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
