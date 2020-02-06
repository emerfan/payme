package com.emer.api.service;

import java.io.IOException;

import javax.mail.MessagingException;

import com.emer.api.model.Transaction;
import com.itextpdf.text.DocumentException;

/**
 * 
 * @author emerfanning
 *
 */
public interface InvoiceService {

	Transaction calculateInvoice(Transaction calculatedInvoice);

	void mailInvoice(Transaction calculatedInvoice) throws IOException, DocumentException, MessagingException;
}
