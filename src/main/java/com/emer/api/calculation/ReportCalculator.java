package com.emer.api.calculation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.emer.api.dao.InvoiceRepository;
import com.emer.api.model.DateSearch;
import com.emer.api.model.SalesReport;

@Component
public class ReportCalculator {
	
	/*
	 *  Repository for the Invoice Table
	 */
	@Autowired
	private InvoiceRepository invoiceDao;
	
	public SalesReport getSalesReport(DateSearch searchParams){
		return this.invoiceDao.getSalesReport(searchParams.getDateFrom(), searchParams.getDateTo());
	}
}
