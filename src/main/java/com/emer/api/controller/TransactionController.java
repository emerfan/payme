package com.emer.api.controller;

import java.io.IOException;
import java.util.List;
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
import com.emer.api.model.Transaction;
import com.emer.api.service.InvoiceService;
import com.emer.api.service.TransactionService;
import com.itextpdf.text.DocumentException;

/**
 * The InvoiceController class Provides REST endpoints for Invoice related
 * operations
 * 
 * @author emerfanning
 *
 */
@RestController
@RequestMapping("/rest/transactions")
public class TransactionController {

	@Autowired
	private TransactionService transactionService;

	@Autowired
	private InvoiceService invoiceServiceImpl;

	/**
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("/{id}")
	public Optional<Transaction> findById(@PathVariable("id") Long id) {
		return transactionService.findOne(id);
	}

	@PostMapping("/{sendmail}")
	public Transaction saveTransaction(@RequestBody Transaction newTransaction,
			@PathVariable("sendmail") Boolean sendmail)
			throws AddressException, MessagingException, IOException, DocumentException, InvalidRequestException {

		return transactionService.save(newTransaction, sendmail);
	}

	/**
	 * 
	 * @param newInvoice
	 * @return
	 */
	@PostMapping("/calculate")
	public Transaction calculateInvoice(@RequestBody Transaction newInvoice) {
		return invoiceServiceImpl.calculateInvoice(newInvoice);
	}

	/**
	 * 
	 * @param id
	 */
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		transactionService.delete(id);
	}

	/**
	 * 
	 * @param dateSearchParams
	 * @return
	 */
	@PostMapping("/search")
	public List<Transaction> search(@RequestBody DateSearch dateSearchParams) {
		return transactionService.search(dateSearchParams);
	}
}
