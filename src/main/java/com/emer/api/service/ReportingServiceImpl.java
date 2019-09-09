package com.emer.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emer.api.calculation.ReportCalculator;
import com.emer.api.model.DateSearch;
import com.emer.api.model.Report;

@Service
public class ReportingServiceImpl implements ReportingService {
	
	@Autowired
	private ReportCalculator reportCalculator;
	
	@Override
	public Report generateReport(DateSearch searchParams) {
		
		Report report = new Report();
		report.setSearchParams(searchParams);
		report.setSalesReport(this.reportCalculator.getSalesReport(searchParams));
		
		return report;
	}
}
