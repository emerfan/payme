package com.emer.api.calculation;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.payme.api.model.Transaction;
import com.payme.api.model.TransactionItem;
import com.payme.api.utils.InvoiceCalculator;

@RunWith(JUnit4.class)
public class InvoiceCalculatorTest {

	// Class under test
	private InvoiceCalculator calc = new InvoiceCalculator();

	@Test
	public void totalInvoiceTest() {
		// Arrange
		// Arrange
		Transaction invoice = new Transaction();
		// Item 1
		TransactionItem itm1 = new TransactionItem();
		itm1.setPrice(new BigDecimal(9.99));
		itm1.setQty(4);

		// Item 2
		TransactionItem itm2 = new TransactionItem();
		itm2.setPrice(new BigDecimal(2.13));
		itm2.setQty(14);

		// Item 3
		TransactionItem itm3 = new TransactionItem();
		itm3.setPrice(new BigDecimal(19.67));
		itm3.setQty(6);

		invoice.addTransactionItem(itm1);
		invoice.addTransactionItem(itm2);
		invoice.addTransactionItem(itm3);

		// Act
		Transaction result = calc.totalInvoice(invoice);

		// Assert
		assertEquals(new BigDecimal(230.99).setScale(2, BigDecimal.ROUND_HALF_EVEN), result.getTotal());
		assertEquals(new BigDecimal(43.19).setScale(2, BigDecimal.ROUND_HALF_EVEN), result.getVat());
		assertEquals(new BigDecimal(187.80).setScale(2, BigDecimal.ROUND_HALF_EVEN), result.getTotalExVat());
	}

	@Test
	public void calculateInvoiceVatTest() {
		// Arrange
		BigDecimal invoiceTotalExVat = new BigDecimal(187.80).setScale(2, BigDecimal.ROUND_HALF_EVEN);

		// Act
		BigDecimal result = calc.calculateInvoiceVat(invoiceTotalExVat);

		// Assert
		assertEquals(new BigDecimal(43.19).setScale(2, BigDecimal.ROUND_HALF_EVEN), result);
	}

	@Test
	public void calculateItemTotalTest() {
		// Arrange
		TransactionItem itm = new TransactionItem();
		itm.setPrice(new BigDecimal(9.99));
		itm.setQty(4);

		// Act
		BigDecimal result = calc.calculateItemTotal(itm);

		// Assert
		assertEquals(new BigDecimal(39.96).setScale(2, BigDecimal.ROUND_HALF_EVEN), result);
	}

	@Test
	public void totalItemsTest() {
		// Arrange
		Transaction invoice = new Transaction();
		// Item 1
		TransactionItem itm1 = new TransactionItem();
		itm1.setPrice(new BigDecimal(9.99));
		itm1.setQty(4);

		// Item 2
		TransactionItem itm2 = new TransactionItem();
		itm2.setPrice(new BigDecimal(2.13));
		itm2.setQty(14);

		// Item 3
		TransactionItem itm3 = new TransactionItem();
		itm3.setPrice(new BigDecimal(19.67));
		itm3.setQty(6);

		invoice.addTransactionItem(itm1);
		invoice.addTransactionItem(itm2);
		invoice.addTransactionItem(itm3);

		// Act
		BigDecimal result = calc.calculateInvoiceTotalExVat(invoice);

		// Assert
		assertEquals(new BigDecimal(187.80).setScale(2, BigDecimal.ROUND_HALF_EVEN), result);
	}
}
