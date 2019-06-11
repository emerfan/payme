package com.emer.api.calculation;

import java.math.RoundingMode;

import org.joda.money.Money;
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
		Double invoiceTotalDbl = 0.00;
		invoiceTotalDbl = totalItems(invoice, invoiceTotalDbl);
		
		Money invoiceTotalExVat = Money.parse("EUR " +invoiceTotalDbl.toString());
		Double vat = invoiceTotalDbl * 0.23;
		Money total = invoiceTotalExVat.plus(vat, RoundingMode.UP);
		
		setInvoiceTotals(invoice, invoiceTotalExVat, vat, total);
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
	private void setInvoiceTotals(Invoice invoice, Money invoiceTotalExVat, Double vat, Money total) {
		invoice.setTotalExVat(invoiceTotalExVat.toString());
		invoice.setVat(vat.toString());
		invoice.setTotal(total.toString());
	}
	
	/**
	 * The totalItems helper method. Gets the total for each item row and
	 * adds it to the full invoice amount.
	 * 
	 * @param invoice
	 * @param invoiceTotalDbl
	 * @return
	 */
	private Double totalItems(Invoice invoice, Double invoiceTotalDbl) {
		for(InvoiceItem item: invoice.getItems()) {
			calculateItemTotal(item);
			invoiceTotalDbl += item.getTotal();
		}
		return invoiceTotalDbl;
	}
	
	/**
	 * The calculateItemTotal helper. Returns the total
	 * of each item row.
	 * 
	 * @param item
	 */
	public void calculateItemTotal(InvoiceItem item) {	
		item.setTotal(item.getPrice() * item.getQty());
	}
	
	/**
	 * 
	 * eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJlbWVyZmFuIiwidXNlcklkIjoiMSIsInJvbGUiOiJhZG1pbiJ9.8qqU9xOaaxQXKBhJVY_rY5eUDnCIS3md3730zWGLWmXlD44n0megcCT_CP4Orn4au0oGtbxHVOf-DGsNTQ6D9w
	 * 
	 */
}
