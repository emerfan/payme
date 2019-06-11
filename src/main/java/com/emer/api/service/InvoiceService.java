package com.emer.api.service;

import java.io.IOException;
import java.util.Optional;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import com.emer.api.model.DateSearch;
import com.emer.api.model.Invoice;
import com.emer.api.model.InvoiceRequest;
import com.itextpdf.text.DocumentException;
/**
 * 
 * @author emerfanning
 *
 */
public interface InvoiceService {
	
	/**
	 * 
	 * @param dateSearchParams
	 * @return
	 */
	Iterable<Invoice> searchInvoices(DateSearch dateSearchParams);
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	Optional<Invoice> getInvoice(Long id);
	
	/**
	 * 
	 * @param id
	 */
	void deleteInvoice(Long id);
	
	/**
	 * 
	 * @param newInvoiceRequest
	 * @return
	 * @throws IOException
	 * @throws DocumentException
	 * @throws AddressException
	 * @throws MessagingException
	 */
	Invoice saveInvoice(InvoiceRequest newInvoiceRequest)
				throws IOException, DocumentException, AddressException, MessagingException;
}
