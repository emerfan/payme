package com.payme.api.service;

import java.io.IOException;
import java.util.Objects;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.springframework.stereotype.Service;

import com.itextpdf.text.DocumentException;
import com.payme.api.model.Transaction;
import com.payme.api.model.Vat;
import com.payme.api.utils.InvoiceCalculator;
import com.payme.api.utils.InvoiceMailer;
import com.payme.api.utils.InvoicePdfBuilder;

@Service
public class InvoiceServiceImpl implements InvoiceService {

	private InvoicePdfBuilder invoicePdfBuilder;
	private InvoiceMailer mailer;
	private InvoiceCalculator invoiceCalculator;

	/**
	 * 
	 * @param invoicePdfBuilder
	 * @param mailer
	 * @param invoiceCalculator
	 */
	public InvoiceServiceImpl(InvoicePdfBuilder invoicePdfBuilder, InvoiceMailer mailer,
			InvoiceCalculator invoiceCalculator) {
		this.invoicePdfBuilder = invoicePdfBuilder;
		this.mailer = mailer;
		this.invoiceCalculator = invoiceCalculator;
	}

	/**
	 * 
	 */
	public Transaction calculateInvoice(Transaction newInvoice) {
		if (Objects.isNull(newInvoice.getTransactionDate())) {
			newInvoice.setTransactionDate(new java.util.Date());
		}

		newInvoice.setVatRate(Vat.TWENTY_THREE_PCT.ordinal());
		newInvoice = this.invoiceCalculator.totalInvoice(newInvoice);

		return newInvoice;
	}

	/**
	 * 
	 * @param newInvoice
	 * @throws IOException
	 * @throws DocumentException
	 * @throws AddressException
	 * @throws MessagingException
	 */
	public void mailInvoice(Transaction newInvoice)
			throws IOException, DocumentException, AddressException, MessagingException {
		byte[] genPdf = invoicePdfBuilder.createPdf(newInvoice);
		this.mailer.mailInvoicePdf(newInvoice, genPdf);
	}
}
