package com.emer.api.service;
import com.emer.api.model.DateSearch;
import com.emer.api.model.PeriodicalReport;

public interface ReportService {

	PeriodicalReport createSalesReportForTimePeriod(DateSearch rangeParams);
}
