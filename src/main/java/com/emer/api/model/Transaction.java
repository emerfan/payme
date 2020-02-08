package com.emer.api.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Transaction {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Temporal(TemporalType.DATE)
	private Date transactionDate;

	private int vatRate;

	private Boolean isPaid;

	private Long tradeEntityId;

	private String tradeEntityName;

	private BigDecimal totalExVat;

	private BigDecimal vat;

	private BigDecimal total;

	private TransactionType transactionType;

	@OneToMany(mappedBy = "transaction", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonManagedReference
	private List<TransactionItem> items = new ArrayList<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}

	public int getVatRate() {
		return vatRate;
	}

	public void setVatRate(int vatRate) {
		this.vatRate = vatRate;
	}

	public Boolean getIsPaid() {
		return isPaid;
	}

	public void setIsPaid(Boolean isPaid) {
		this.isPaid = isPaid;
	}

	public Long getTradeEntityId() {
		return tradeEntityId;
	}

	public void setTradeEntityId(Long tradeEntityId) {
		this.tradeEntityId = tradeEntityId;
	}

	public String getTradeEntityName() {
		return tradeEntityName;
	}

	public void setTradeEntityName(String tradeEntityName) {
		this.tradeEntityName = tradeEntityName;
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

	public TransactionType getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(TransactionType transactionType) {
		this.transactionType = transactionType;
	}

	public List<TransactionItem> getItems() {
		return items;
	}

	public void setItems(List<TransactionItem> items) {
		this.items = items;
	}

	public void addTransactionItem(TransactionItem item) {
		items.add(item);
		item.setTransaction(this);
	}
}
