package com.emer.api.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author emerfanning
 * Model class for the Expense
 * Used by Hibernate to generate the Expense table
 *
 */
@Entity
public class Expense {
	
	@Id
	private Long id;
    
	@Temporal(TemporalType.DATE)
	private Date expenseDate;
	
	private Boolean isPaid;
	
	private Long supplierId;
	
	private BigDecimal totalExVat;
	
	private BigDecimal vat;
	
	private BigDecimal total;
	
	private String invoiceId;
	
	public Expense() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getExpenseDate() {
		return expenseDate;
	}

	public void setExpenseDate(Date expenseDate) {
		this.expenseDate = expenseDate;
	}

	public Boolean getIsPaid() {
		return isPaid;
	}

	public void setIsPaid(Boolean isPaid) {
		this.isPaid = isPaid;
	}

	public Long getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(Long supplierId) {
		this.supplierId = supplierId;
	}

	public BigDecimal getTotalExVat() {
		return totalExVat;
	}

	public void setTotalExVat(BigDecimal totalExVat) {
		this.totalExVat = totalExVat;
	}

	public BigDecimal getVat() {
		return vat;
	}

	public void setVat(BigDecimal vat) {
		this.vat = vat;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public String getInvoiceId() {
		return invoiceId;
	}

	public void setInvoiceId(String invoiceId) {
		this.invoiceId = invoiceId;
	}	
}
