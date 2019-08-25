package com.emer.api.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emer.api.calculation.InvoiceCalculator;
import com.emer.api.dao.InvoiceRepository;
import com.emer.api.exception.InvalidRequestException;
import com.emer.api.model.Customer;
import com.emer.api.model.DateSearch;
import com.emer.api.model.Invoice;
import com.emer.api.model.NewInvoiceRequest;
import com.emer.api.model.Vat;
import com.emer.api.utils.InvoicePdfBuilder;
import com.emer.api.validation.InvoiceValidator;
import com.emer.api.utils.InvoiceMailer;
import com.itextpdf.text.DocumentException;

@Service
public class InvoiceServiceImpl implements InvoiceService {

	/*
	 *  Repository for the Invoice Table
	 */
	@Autowired
	private InvoiceRepository invoiceDao;
	
	/*
	 * Helper class for the invoices
	 */
	@Autowired
	private InvoicePdfBuilder invoiceUtility;
	
	/*
	 * Mailer
	 */
	@Autowired
	private InvoiceMailer mailer;
	
	/*
	 * Customer Service
	 */
	@Autowired
	private CustomerServiceImpl customerService;
	
	/*
	 * Calculator
	 */
	@Autowired
	private InvoiceCalculator calc;
	
	/*
	 * Validator
	 */
	@Autowired
	private InvoiceValidator invoiceValidator;
	
	
	/**
	 * 
	 * @param id
	 */
	@Override
	public void deleteInvoice(Long id) {
		invoiceDao.deleteById(id);
	}
	/**
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public Optional<Invoice> getInvoice(Long id) {
		Optional<Invoice> invoice = invoiceDao.findById(id);
		if(invoice.isPresent()) {
			setSalonName(invoice);
		}		
		return invoice;
	}
	
	/**
	 * 
	 * @param dateSearchParams
	 * @return
	 */
	@Override
	public List<Invoice> searchInvoices(DateSearch dateSearchParams) {
		Iterable<Invoice> invoices = null;
		if (isFullSearch(dateSearchParams)) {
			invoices = invoiceDao
					.findByCustomerIdAndIsPaidAndInvoiceDateBetween(dateSearchParams.getCustomerId(),
							dateSearchParams.getPaidStatus(), dateSearchParams.getDateFrom(), 
							dateSearchParams.getDateTo());
			return determineCustomerDetails(invoices);
			}
		if(isSearchTimePeriodForStatus(dateSearchParams)) {
			invoices = invoiceDao.findByIsPaidAndInvoiceDateBetween(dateSearchParams.getPaidStatus(), dateSearchParams.getDateFrom(), 
							dateSearchParams.getDateTo());
			return determineCustomerDetails(invoices);
		}
		if(isSearchTimePeriodForCustomer(dateSearchParams)) {
			invoices = invoiceDao.findByCustomerIdAndInvoiceDateBetween(dateSearchParams.getCustomerId(), dateSearchParams.getDateFrom(), 
							dateSearchParams.getDateTo());
			return determineCustomerDetails(invoices);
		}
		if(isSearchTimePeriod(dateSearchParams)) {
			invoices =  invoiceDao.findByInvoiceDateBetween(dateSearchParams.getDateFrom(), dateSearchParams.getDateTo());
			return determineCustomerDetails(invoices);
		}
		return null;
	}
	
	/**
	 * 
	 * @param newInvoice
	 * @return
	 * @throws IOException
	 * @throws DocumentException
	 * @throws AddressException
	 * @throws MessagingException
	 * @throws InvalidRequestException 
	 */
	@Override
	public Invoice saveInvoice(NewInvoiceRequest newInvoiceRequest)
			throws IOException, DocumentException, AddressException, MessagingException, InvalidRequestException {
		
		this.invoiceValidator.validateNewInvoiceRequest(newInvoiceRequest);
		Invoice newInvoice = newInvoiceRequest.getNewInvoice();
    
		if(Objects.isNull(newInvoice.getId())) {
			this.setInvoiceId(newInvoice);
		}
		
		if(newInvoiceRequest.isCalculateInvoice()) {
			setInvoiceId(newInvoice);
			newInvoice.setInvoiceDate(new java.util.Date());
			newInvoice = calc.totalInvoice(newInvoice);
		}		
		
		newInvoice.setVatRate(Vat.TWENTY_THREE_PCT.ordinal());
		newInvoice = this.calc.totalInvoice(newInvoice);

		if(newInvoiceRequest.isSendMail()) {
			this.mailInvoice(newInvoice, 
					this.customerService.findByCustomerId(newInvoice.getCustomerId()));
		}
		return this.invoiceDao.save(newInvoice);
	}
	
	/**
	 * 
	 * @param newInvoice
	 */
	private void setInvoiceId(Invoice newInvoice) {
		Long nextId = invoiceDao.getLastId();
		Long currentId = nextId+1;	
		newInvoice.setId(currentId);
	}
	
	/**
	 * 
	 * @param newInvoice
	 * @param name
	 * @throws IOException
	 * @throws DocumentException
	 * @throws AddressException
	 * @throws MessagingException
	 */
	private void mailInvoice(Invoice newInvoice, Optional<Customer> optional)
			throws IOException, DocumentException, AddressException, MessagingException {
		byte[] genPdf = invoiceUtility.createPdf(newInvoice, optional.get().getSalonName());
		this.mailer.mailInvoicePdf(newInvoice, optional.get().getSalonName(), genPdf);
	}
	
	/**
	 * 
	 * @param dateSearchParams
	 * @return
	 */
	private boolean isFullSearch(DateSearch dateSearchParams) {
		return dateSearchParams.getPaidStatus() != null &&
				dateSearchParams.getCustomerId() != null && dateSearchParams.getDateFrom() != null
				&& dateSearchParams.getDateTo() != null;
	}
	
	/**
	 * 
	 * @param dateSearchParams
	 * @return
	 */
	private boolean isSearchTimePeriodForStatus(DateSearch dateSearchParams) {
		return dateSearchParams.getPaidStatus() != null &&
				dateSearchParams.getDateFrom() != null
				&& dateSearchParams.getDateTo() != null;
	}
	
	/**
	 * 
	 * @param dateSearchParams
	 * @return
	 */
	private boolean isSearchTimePeriodForCustomer(DateSearch dateSearchParams) {
		return dateSearchParams.getCustomerId() != null &&
				dateSearchParams.getDateFrom() != null
				&& dateSearchParams.getDateTo() != null;
	}
	
	/**
	 * 
	 * @param dateSearchParams
	 * @return
	 */
	private boolean isSearchTimePeriod(DateSearch dateSearchParams) {
		return dateSearchParams.getDateFrom() != null
				&& dateSearchParams.getDateTo() != null;
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	private String getSalonName(Long id) {
		return this.customerService.findByCustomerId(id).get().getSalonName();
	}
	
	/**
	 * 
	 * @param invoice
	 */
	private void setSalonName(Optional<Invoice> invoice) {
		String name = this.getSalonName(invoice.get().getCustomerId());
		invoice.get().setSalonName(name);
	}
	
	/**
	 * 
	 * @param invoices
	 * @return
	 */
	private List<Invoice> determineCustomerDetails(Iterable<Invoice> invoices) {
		List<Long> ids = StreamSupport.stream(invoices.spliterator(), false)
				.map(x -> x.getCustomerId())
				.collect(Collectors.toList());
		
		Map<Long, String> customerDetails = new HashMap<Long, String>();
		
		for(Long id: ids) {
			customerDetails.put(id, this.getSalonName(id));
		}
		
		List<Invoice> invoicesWithDetails = StreamSupport.stream(invoices.spliterator(), false)
				.map(x -> this.updateInvoiceWithSalonName(x, customerDetails))
				.collect(Collectors.toList());
		
		return invoicesWithDetails;
	}
	
	/**
	 * 
	 * @param invoice
	 * @param customerDetails
	 * @return
	 */
	private Invoice updateInvoiceWithSalonName(Invoice invoice, Map<Long, String> customerDetails) {
		invoice.setSalonName(customerDetails.get(invoice.getCustomerId()));
		return invoice;
	}
}
