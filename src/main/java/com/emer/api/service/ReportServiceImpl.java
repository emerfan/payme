package com.emer.api.service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emer.api.model.DateSearch;
import com.emer.api.model.Invoice;
import com.emer.api.model.PeriodicalReport;

@Service
public class ReportServiceImpl implements ReportService {
	
	@Autowired
	private InvoiceService invoiceService;
	
	@Override
	public PeriodicalReport createSalesReportForTimePeriod(DateSearch rangeParams) {
		List<Invoice> invoices = this.invoiceService.searchInvoices(rangeParams);
		PeriodicalReport report = this.determineReportFromInvoices(invoices);
		report.setTimePeriod(rangeParams);
		return report;
	}
	
	/**
	 * 
	 * @param invoices
	 * @return
	 */
	private PeriodicalReport determineReportFromInvoices(List<Invoice> invoices) {
		PeriodicalReport report = new PeriodicalReport();
		Double total = new Double(0);
		Double totalExVat = new Double(0);
		Double totalVat = new Double(0);
		
		for(Invoice invoice: invoices) {
			total += Double.parseDouble(invoice.getTotal());
			totalExVat += Double.parseDouble(invoice.getTotalExVat());
			totalVat += Double.parseDouble(invoice.getVat());
		}
		
		report.setTotalExVatSales(totalExVat.toString());
		report.setTotalSales(total.toString());
		report.setVatSales(totalVat.toString());

		return report;
	}	
}
