package com.emer.api.controllers;

import java.io.IOException;
import java.util.Optional;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.emer.api.exception.InvalidRequestException;
import com.emer.api.model.DateSearch;
import com.emer.api.model.Invoice;
import com.emer.api.model.NewInvoiceRequest;
import com.emer.api.service.InvoiceService;
import com.itextpdf.text.DocumentException;

/**
 * The InvoiceController class
 * Provides REST endpoints for Invoice related operations
 * 
 * @author emerfanning
 *
 */
@RestController
@RequestMapping("/rest/invoices")
public class InvoiceController {
	
	/*
	 *  Invoice Service
	 */
	@Autowired
	private InvoiceService invoiceService;
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("/{id}")
	public Optional<Invoice> findById(@PathVariable("id") Long id) {
		return invoiceService.getInvoice(id);
	}
	
	/**
	 * 
	 * @param newInvoiceRequest
	 * @return
	 * @throws AddressException
	 * @throws MessagingException
	 * @throws IOException
	 * @throws DocumentException
	 * @throws InvalidRequestException 
	 */
	@PostMapping()
	public Invoice addInvoice(@RequestBody NewInvoiceRequest newInvoiceRequest) 
			throws AddressException, MessagingException, IOException, DocumentException, InvalidRequestException {
		
		return invoiceService.saveInvoice(newInvoiceRequest);
	}
	
	/**
	 * 
	 * @param id
	 */
	@DeleteMapping("/{id}")
	public void deleteInvoice(@PathVariable Long id){
		invoiceService.deleteInvoice(id);
	}
	
	/**
	 * 
	 * @param dateSearchParams
	 * @return
	 */
	@PostMapping("/search")
	public Iterable<Invoice> search(@RequestBody DateSearch dateSearchParams) {
		return invoiceService.searchInvoices(dateSearchParams);
	}
}
