package com.emer.api.dao;

import java.util.Date;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.emer.api.model.FinancialReport;
import com.emer.api.model.Invoice;

/**
 * @author emerfanning Jpa Repository for Invoices
 *
 */
@Repository
@Transactional
public interface InvoiceRepository extends JpaRepository<Invoice, Long> {

	/**
	 * The findByIds method. Return the invoice matching the id passed.
	 */
	Optional<Invoice> findById(Long id);

	/**
	 * The findByCustomerId method. Retrieve all invoices with a customer id
	 * matching the one passed
	 * 
	 * @param id
	 * @return
	 */
	Iterable<Invoice> findByCustomerId(Long id);

	/**
	 * The findByIsPaid method. Retrieve all invoices matching the paid status
	 * passed.
	 * 
	 * @param isPaid
	 * @return
	 */
	Iterable<Invoice> findByIsPaid(Boolean isPaid);

	/**
	 * The findByInvoiceDate method.
	 * 
	 * Retrieve all invoices with a date matching the date passed.
	 * 
	 * @param invoiceDate
	 * @return
	 */
	Iterable<Invoice> findByInvoiceDate(Date invoiceDate);

	/**
	 * The findByInvoiceDateBetween method. Retrieve all invoices between the dates
	 * passed.
	 * 
	 * @param invoiceDateStart
	 * @param invoiceDateEnd
	 * @return
	 */
	Iterable<Invoice> findByInvoiceDateBetween(Date invoiceDateStart, Date invoiceDateEnd);

	/**
	 * The findByCustomerIdAndInvoiceDateBetween method. Find by customer id and
	 * between the dates passed.
	 * 
	 * @param customerId
	 * @param invoiceDateStart
	 * @param invoiceDateEnd
	 * @return
	 */
	Iterable<Invoice> findByCustomerIdAndInvoiceDateBetween(Long customerId, Date invoiceDateStart,
			Date invoiceDateEnd);

	/**
	 * The findByIsPaidAndInvoiceDateBetween method. Find all invoices with the
	 * status passed and between the dates passed.
	 * 
	 * @param isPaid
	 * @param invoiceDateStart
	 * @param invoiceDateEnd
	 * @return
	 */
	Iterable<Invoice> findByIsPaidAndInvoiceDateBetween(Boolean isPaid, Date invoiceDateStart, Date invoiceDateEnd);

	/**
	 * The findByCustomerIdAndIsPaidAndInvoiceDateBetween method Search by customer
	 * id, paid status and between the dates passed.
	 * 
	 * @param customerId
	 * @param isPaid
	 * @param invoiceDateStart
	 * @param invoiceDateEnd
	 * @return
	 */
	Iterable<Invoice> findByCustomerIdAndIsPaidAndInvoiceDateBetween(Long customerId, Boolean isPaid,
			Date invoiceDateStart, Date invoiceDateEnd);

	/**
	 * The getLastId for Invoice method. Used to retrieve the last id in the DB in
	 * order to set the next one
	 * 
	 * @return
	 */
	@Query("SELECT MAX(id) FROM Invoice")
	Long getLastId();

	/**
	 * The getSalesReport method. Returns the SUM of the sales vat, exvat and total
	 * for the given dates
	 * 
	 * @return
	 */
	@Query(value = "SELECT SUM(vat) as salesVat, SUM(total_ex_vat) as salesTotalExVat"
			+ ", SUM(total) as salesTotal FROM invoice WHERE invoice_date BETWEEN ?1 AND ?2", nativeQuery = true)
	FinancialReport getSalesReport(Date invoiceDateStart, Date invoiceDateEnd);
}
