package com.emer.api.service;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.emer.api.TestConfig;
import com.emer.api.model.Transaction;
import com.emer.api.model.TransactionItem;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
public class InvoiceServiceTest {

	@Autowired
	private InvoiceService invoiceService;

	@Test
	public void testCalculateInvoice() {
		// Arrange
		Transaction invoice = new Transaction();
		TransactionItem itm1 = new TransactionItem();
		itm1.setPrice(new BigDecimal(9.99));
		itm1.setQty(4);
		invoice.addTransactionItem(itm1);

		// Act
		invoiceService.calculateInvoice(invoice);

		// Assert
		assertEquals(BigDecimal.valueOf(39.96), invoice.getTotalExVat());
		assertEquals(BigDecimal.valueOf(49.15), invoice.getTotal());
		assertEquals(BigDecimal.valueOf(9.19), invoice.getVat());
	}
}
