package com.emer.api.model;

import java.math.BigDecimal;

public class SalesReport {
	
	private BigDecimal salesTotalExVat;
	
	private BigDecimal salesVat;
	
	private BigDecimal salesTotal;
	
	public SalesReport() {	
	}

	public BigDecimal getSalesTotalExVat() {
		return salesTotalExVat;
	}

	public void setSalesTotalExVat(BigDecimal salesTotalExVat) {
		this.salesTotalExVat = salesTotalExVat;
	}

	public BigDecimal getSalesVat() {
		return salesVat;
	}

	public void setSalesVat(BigDecimal salesVat) {
		this.salesVat = salesVat;
	}

	public BigDecimal getSalesTotal() {
		return salesTotal;
	}

	public void setSalesTotal(BigDecimal salesTotal) {
		this.salesTotal = salesTotal;
	}
}
