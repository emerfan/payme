package com.emer.api.calculation;
import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Component;

import com.emer.api.model.Invoice;
import com.emer.api.model.SalesReport;

@Component
public class ReportCalculator {
	
	/**
	 * 
	 * @param invoices
	 * @return
	 */
	public SalesReport calculateSalesReport(List<Invoice> invoices) {
		BigDecimal salesTotal = BigDecimal.ZERO;
		BigDecimal salesTotalVat = BigDecimal.ZERO;
		BigDecimal salesTotalExVat = BigDecimal.ZERO;
		
		
		for(Invoice invoice : invoices) {
			salesTotal = salesTotal.add(invoice.getTotal());
			
			System.out.println(salesTotal);
			salesTotalVat = salesTotalVat.add(invoice.getVat());
			salesTotalExVat = salesTotalExVat.add(invoice.getTotalExVat());
		}
		
		SalesReport salesReport = new SalesReport();
		salesReport.setSalesTotal(salesTotal);
		salesReport.setSalesTotalExVat(salesTotalExVat);
		salesReport.setSalesVat(salesTotalVat);
		
		return salesReport;	
	}
}
