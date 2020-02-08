package com.payme.api.utils;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

import com.payme.api.model.Transaction;
import com.payme.api.model.TransactionItem;

@Component
public class InvoiceCalculator {

	public Transaction totalInvoice(Transaction invoice) {

		BigDecimal invoiceTotalExVat = this.calculateInvoiceTotalExVat(invoice);
		BigDecimal vat = this.calculateInvoiceVat(invoiceTotalExVat);
		BigDecimal totalInclVat = invoiceTotalExVat.add(vat);

		invoice.setTotalExVat(invoiceTotalExVat);
		invoice.setVat(vat);
		invoice.setTotal(totalInclVat);

		return invoice;
	}

	public BigDecimal calculateInvoiceVat(BigDecimal invoiceTotalExVat) {
		BigDecimal vat = invoiceTotalExVat.multiply(new BigDecimal(0.23));
		vat = vat.setScale(2, BigDecimal.ROUND_HALF_EVEN);
		return vat;
	}

	public BigDecimal calculateInvoiceTotalExVat(Transaction invoice) {

		if (invoice.getTotalExVat() != null) {
			return invoice.getTotalExVat();
		} else {
			BigDecimal invoiceTotalExVat = BigDecimal.ZERO;

			for (TransactionItem item : invoice.getItems()) {
				item.setTotal(this.calculateItemTotal(item));
				invoiceTotalExVat = invoiceTotalExVat.add(item.getTotal());
			}

			return invoiceTotalExVat;
		}
	}

	public BigDecimal calculateItemTotal(TransactionItem item) {
		BigDecimal itemTotal = item.getPrice().multiply(new BigDecimal(item.getQty())).setScale(2,
				BigDecimal.ROUND_HALF_EVEN);

		return itemTotal;
	}
}
