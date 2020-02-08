package com.payme.api.service;

import java.io.IOException;

import javax.mail.MessagingException;

import com.itextpdf.text.DocumentException;
import com.payme.api.model.Transaction;

/**
 * 
 * @author emerfanning
 *
 */
public interface InvoiceService {

	Transaction calculateInvoice(Transaction calculatedInvoice);

	void mailInvoice(Transaction calculatedInvoice) throws IOException, DocumentException, MessagingException;
}
