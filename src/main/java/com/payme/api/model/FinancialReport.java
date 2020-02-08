package com.payme.api.model;

import java.math.BigDecimal;

public interface FinancialReport {
	
	BigDecimal getSalesTotalExVat();
	
	BigDecimal getSalesVat();
	
	BigDecimal getSalesTotal();	
}
