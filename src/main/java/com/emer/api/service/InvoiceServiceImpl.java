package com.emer.api.service;

import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emer.api.calculation.InvoiceCalculator;
import com.emer.api.dao.InvoiceRepository;
import com.emer.api.model.Customer;
import com.emer.api.model.DateSearch;
import com.emer.api.model.Invoice;
import com.emer.api.model.InvoiceRequest;
import com.emer.api.model.Vat;
import com.emer.api.utils.InvoicePdfBuilder;
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
		return invoiceDao.findById(id);
	}
	
	/**
	 * 
	 * @param dateSearchParams
	 * @return
	 */
	@Override
	public Iterable<Invoice> searchInvoices(DateSearch dateSearchParams) {
		if (isFullSearch(dateSearchParams)) {
			return invoiceDao
					.findByCustomerIdAndIsPaidAndInvoiceDateBetween(dateSearchParams.getCustomerId(),
							dateSearchParams.getPaidStatus(), dateSearchParams.getDateFrom(), 
							dateSearchParams.getDateTo());
			}
		if(isSearchTimePeriodForStatus(dateSearchParams)) {
			return invoiceDao.findByIsPaidAndInvoiceDateBetween(dateSearchParams.getPaidStatus(), dateSearchParams.getDateFrom(), 
							dateSearchParams.getDateTo());
		}
		if(isSearchTimePeriodForCustomer(dateSearchParams)) {
			return invoiceDao.findByCustomerIdAndInvoiceDateBetween(dateSearchParams.getCustomerId(), dateSearchParams.getDateFrom(), 
							dateSearchParams.getDateTo());
		}
		if(isSearchTimePeriod(dateSearchParams)) {
			return invoiceDao.findByInvoiceDateBetween(dateSearchParams.getDateFrom(), dateSearchParams.getDateTo());
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
	 */
	@Override
	public Invoice saveInvoice(InvoiceRequest newInvoiceRequest)
			throws IOException, DocumentException, AddressException, MessagingException {
		Invoice newInvoice = newInvoiceRequest.getNewInvoice();

		if(Objects.isNull(newInvoice.getId())) {
			setInvoiceId(newInvoice);
		}
		
		if(Objects.isNull(newInvoice.getInvoiceDate())) {
			newInvoice.setInvoiceDate(new java.util.Date());
		}
		
		newInvoice.setVatRate(Vat.TWENTY_THREE_PCT.ordinal());
		
		newInvoice = calc.totalInvoice(newInvoice);
		
		if(newInvoiceRequest.isSendMail()) {
			mailInvoice(newInvoice, 
					this.customerService.findByCustomerId(newInvoice.getCustomerId()));
		}
	
		return invoiceDao.save(newInvoice);
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
		mailer.mailInvoicePdf(newInvoice, optional.get().getSalonName(), genPdf);
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
}
