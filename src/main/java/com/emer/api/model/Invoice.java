package com.emer.api.model;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonManagedReference;


/**
 * @author emerfanning
 * Model class for the Invoice
 * Used by Hibernate to generate the Invoice table
 *
 */
@Entity
public class Invoice {
	
  @Id
  private Long id;
    
	@Temporal(TemporalType.DATE)
	private Date invoiceDate;

	private int vatRate;
	
	private Boolean isPaid;
	
	private Long customerId;

	private String salonName;
	
	private BigDecimal totalExVat;
	
	private BigDecimal vat;
	
	private BigDecimal total;
	
    public Invoice() {
	}

	@OneToMany(
        mappedBy = "invoice", 
        cascade = CascadeType.ALL, 
        orphanRemoval = true
    )
	@JsonManagedReference
    private List<InvoiceItem> items = new ArrayList<>();

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

	public int getVatRate() {
		return vatRate;
	}

	public void setVatRate(Integer rate) {
		this.vatRate = rate;
	}

	public Boolean getIsPaid() {
		return isPaid;
	}

	public void setIsPaid(Boolean isPaid) {
		this.isPaid = isPaid;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
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

	public void setVatRate(int vatRate) {
		this.vatRate = vatRate;
	}

	public String getSalonName() {
		return salonName;
	}

	public void setSalonName(String salonName) {
		this.salonName = salonName;
	}

	@Override
	public String toString() {
		return "Invoice [id=" + id + ", invoiceDate=" + invoiceDate + ", vatRate=" + vatRate + ", isPaid=" + isPaid
				+ ", customerId=" + customerId + ", items=" + items + "]";
	}
}
