package com.emer.api.calculation;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import org.springframework.stereotype.Component;

import com.emer.api.model.Invoice;
import com.emer.api.model.InvoiceItem;

@Component
public class InvoiceCalculator {
	
	public Invoice totalInvoice(Invoice invoice) {
		
		BigDecimal invoiceTotalExVat = this.calculateInvoiceTotalExVat(invoice);
		BigDecimal vat = calculateInvoiceVat(invoiceTotalExVat);
		BigDecimal totalIncVat = invoiceTotalExVat.add(vat);
		
		this.setInvoiceTotals(invoice, invoiceTotalExVat, vat, totalIncVat);
		return invoice;
	}

	public BigDecimal calculateInvoiceVat(BigDecimal invoiceTotalExVat) {
		BigDecimal vat = invoiceTotalExVat
				.multiply(new BigDecimal(0.23));
		vat = vat.setScale(2, BigDecimal.ROUND_HALF_EVEN);
		return vat;
	}
	
	public BigDecimal calculateInvoiceTotalExVat(Invoice invoice) {
		BigDecimal invoiceTotal = BigDecimal.ZERO;
		
		for(InvoiceItem item: invoice.getItems()) {
			item.setTotal(this.calculateItemTotal(item));
			invoiceTotal = invoiceTotal.add(item.getTotal());
		}
		
		return invoiceTotal;
	}
	
	public BigDecimal calculateItemTotal(InvoiceItem item) {	
		BigDecimal itemTotal = item
				.getPrice()
				.multiply(new BigDecimal(item.getQty()))
				.setScale(2, BigDecimal.ROUND_HALF_EVEN);
		
		return itemTotal;
	}
	
	public void setInvoiceTotals(Invoice invoice, BigDecimal invoiceTotalExVat, 
			BigDecimal vat, BigDecimal totalInclVat) {
		
		invoice.setTotalExVat(invoiceTotalExVat);
		invoice.setVat(vat);
		invoice.setTotal(totalInclVat);
	}
}
