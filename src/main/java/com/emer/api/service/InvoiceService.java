package com.emer.api.service;

import java.io.IOException;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import com.emer.api.exception.InvalidRequestException;
import com.emer.api.model.Transaction;
import com.itextpdf.text.DocumentException;

/**
 * 
 * @author emerfanning
 *
 */
public interface InvoiceService {

	/**
	 * 
	 * @param newInvoiceRequest
	 * @return
	 * @throws IOException
	 * @throws DocumentException
	 * @throws AddressException
	 * @throws MessagingException
	 * @throws InvalidRequestException
	 */
	Transaction calculateInvoice(Transaction calculatedInvoice);
}
