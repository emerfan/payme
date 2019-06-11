package com.emer.api.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import com.fasterxml.jackson.annotation.JsonBackReference;


/**
 * @author emerfanning
 * Model class for the Invoice Items
 *
 */
@Entity
public class InvoiceItem {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "invoice_id")
	@JsonBackReference
    private Invoice invoice;
	
	private String name;
	
	private Integer qty;
	
	private Double price;
	
	private Double total;
	
	public InvoiceItem(Invoice invoice, String name, Integer qty, Double price, Double total) {
		this.invoice = invoice;
		this.name = name;
		this.qty = qty;
		this.price = price;
		this.total = total;
	}

	public InvoiceItem() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Invoice getInvoice() {
		return invoice;
	}

	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getQty() {
		return qty;
	}

	public void setQty(Integer qty) {
		this.qty = qty;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}	
}
