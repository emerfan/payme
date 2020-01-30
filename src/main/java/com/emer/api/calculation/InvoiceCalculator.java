package com.emer.api.calculation;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

import com.emer.api.model.Invoice;
import com.emer.api.model.InvoiceItem;

@Component
public class InvoiceCalculator {

	public Invoice totalInvoice(Invoice invoice) {

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

	public BigDecimal calculateInvoiceTotalExVat(Invoice invoice) {

		if (invoice.getTotalExVat() != null) {
			return invoice.getTotalExVat();
		} else {
			BigDecimal invoiceTotalExVat = BigDecimal.ZERO;

			for (InvoiceItem item : invoice.getItems()) {
				item.setTotal(this.calculateItemTotal(item));
				invoiceTotalExVat = invoiceTotalExVat.add(item.getTotal());
			}

			return invoiceTotalExVat;
		}
	}

	public BigDecimal calculateItemTotal(InvoiceItem item) {
		BigDecimal itemTotal = item.getPrice().multiply(new BigDecimal(item.getQty())).setScale(2,
				BigDecimal.ROUND_HALF_EVEN);

		return itemTotal;
	}
}
