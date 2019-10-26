package com.emer.api.model;

import java.math.BigDecimal;

public interface SalesReport {
	
	BigDecimal getSalesTotalExVat();
	
	BigDecimal getSalesVat();
	
	BigDecimal getSalesTotal();
}
