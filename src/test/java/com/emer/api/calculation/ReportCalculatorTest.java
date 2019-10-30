package com.emer.api.calculation;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.emer.api.dao.InvoiceRepository;
import com.emer.api.model.DateSearch;
import com.emer.api.model.SalesReport;

@RunWith(MockitoJUnitRunner.class)
public class ReportCalculatorTest {
	
	@InjectMocks
	private ReportCalculator calc;
		
	@Mock
	private InvoiceRepository invoiceDao;
	
	private SalesReport report;
	
	private class SalesReportMock implements SalesReport{

		@Override
		public BigDecimal getSalesTotalExVat() {
			return null;
		}

		@Override
		public BigDecimal getSalesVat() {
			return null;
		}

		@Override
		public BigDecimal getSalesTotal() {
			return null;
		}
		
	}
	
	@Before
	public void before(){
		report = new SalesReportMock();
	}
	
	@Test
	public void testGetSalesReport() {
		// Arrange
		when(invoiceDao.getSalesReport(Matchers.any(), Matchers.any()))
		.thenReturn(report);
		
		// Act
		SalesReport result = calc.getSalesReport(new DateSearch());
		
		// Assert
		assertEquals(report, result);
	}
}
