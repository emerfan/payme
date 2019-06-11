package com.emer.api.model;

/**
 * @author emerfanning
 * Vat set as enum, can be added to should the rate change while maintaining
 * historical data
 *
 */
public enum Vat {
	
	TWENTY_THREE_PCT (0.23);
	
	private Double vatRate;
	
	Vat (double rate) {
		this.vatRate = rate;
	}
	
	public Double getVatRate() {
		return this.vatRate;
	}
}
