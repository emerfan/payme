package com.emer.api.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.emer.api.model.DateSearch;
import com.emer.api.model.Report;
import com.emer.api.service.ReportingService;
/**
 * The ReportController class
 * Provides REST endpoints for Report related operations
 * 
 * @author emerfanning
 *eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJlbWVyZmFuIiwidXNlcklkIjoiMSIsInJvbGUiOiJhZG1pbiJ9.
 *Lp-KxNiYgK-yUtN-6Kj66olxNuEAeK2gUQ_6xprbN04h4J4Z5ArSuZmIfN6yTYrt4JKrAjScyXaFIanomG3tgQ
 */
@RestController
@RequestMapping("/rest/reports")
public class ReportController {
	
	@Autowired
	private ReportingService reportService;
	
	/**
	 * 
	 * @param dateSearchParams
	 * @return
	 */
	@PostMapping()
	public Report getReport(@RequestBody DateSearch dateSearchParams) {
		return reportService.generateReport(dateSearchParams);
	}
}
