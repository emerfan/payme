package com.emer.api.service;

import com.emer.api.model.DateSearch;
import com.emer.api.model.Report;

public interface ReportingService {

	Report generateReport(DateSearch searchParams);
}
