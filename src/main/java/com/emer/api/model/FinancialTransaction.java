package com.emer.api.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class FinancialTransaction {
	
	@Id Long id;

	@Temporal(TemporalType.DATE) Date invoiceDate;
	
	@OneToMany(
			mappedBy = "invoice", 
			cascade = CascadeType.ALL, 
			orphanRemoval = true
			)
	@JsonManagedReference
	private List<InvoiceItem> items = new ArrayList<>();
	
	private Boolean isPaid;
	
	private BigDecimal totalExVat;

	private BigDecimal vat;

	private BigDecimal total;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<InvoiceItem> getItems() {
		return items;
	}

	public void setItems(List<InvoiceItem> items) {
		this.items = items;
	}

	public void addItem(InvoiceItem item) {
		items.add(item);
		item.setInvoice(this);
	}

	public Date getInvoiceDate() {
		return invoiceDate;
	}

	public void setInvoiceDate(Date invoiceDate) {
		this.invoiceDate = invoiceDate;
	}
	
	public Boolean getIsPaid() {
		return isPaid;
	}

	public void setIsPaid(Boolean isPaid) {
		this.isPaid = isPaid;
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
}
