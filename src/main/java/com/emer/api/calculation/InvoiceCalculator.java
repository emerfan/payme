package com.emer.api.calculation;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import org.springframework.stereotype.Component;

import com.emer.api.model.Invoice;
import com.emer.api.model.InvoiceItem;
/**
 * 
 * This class provides calculator utility methods
 * to total the invoice.
 * 
 * @author emerfanning
 *
 */
@Component
public class InvoiceCalculator {
	
	/**
	 * The totalInvoice method. Takes in an invoice and
	 * calculates the total of each invoice item and
	 * the final invoice total.
	 * 
	 * @param invoice
	 * @return
	 */
	public Invoice totalInvoice(Invoice invoice) {
		BigDecimal invoiceTotal = BigDecimal.ZERO;
		invoiceTotal = this.totalItems(invoice);
		MathContext mc = new MathContext(2, RoundingMode.UP);
		BigDecimal vat = invoiceTotal.multiply(new BigDecimal(0.23), mc);
		BigDecimal total = invoiceTotal.add(vat);
		
		this.setInvoiceTotals(invoice, invoiceTotal, vat, total);
		return invoice;
	}
	
	/**
	 * The setInvoiceTotals helper method. Updates the invoice VAT, SUB and TOTAL
	 * 
	 * @param invoice
	 * @param invoiceTotalExVat
	 * @param vat
	 * @param total
	 */
	private void setInvoiceTotals(Invoice invoice, BigDecimal invoiceTotalExVat, 
			BigDecimal vat, BigDecimal total) {
		invoice.setTotalExVat(invoiceTotalExVat);
		invoice.setVat(vat);
		invoice.setTotal(total);
	}
	
	/**
	 * The totalItems helper method. Gets the total for each item row and
	 * adds it to the full invoice amount.
	 * 
	 * @param invoice
	 * @param invoiceTotalDbl
	 * @return
	 */
	private BigDecimal totalItems(Invoice invoice) {
		BigDecimal invoiceTotal = BigDecimal.ZERO;
		for(InvoiceItem item: invoice.getItems()) {
			this.calculateItemTotal(item);
			invoiceTotal = invoiceTotal.add(item.getTotal());
		}
		return invoiceTotal;
	}
	
	/**
	 * The calculateItemTotal helper. Returns the total
	 * of each item row.
	 * 
	 * @param item
	 */
	public void calculateItemTotal(InvoiceItem item) {	
		BigDecimal itemTotal = item.getPrice().multiply(new BigDecimal(item.getQty()));
		item.setTotal(itemTotal);
	}
}
