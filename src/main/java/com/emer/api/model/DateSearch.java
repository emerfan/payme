package com.emer.api.model;

import java.util.Date;

/**
 * @author emerfanning
 * Model used to pass parameters for searching by date
 *
 */
public class DateSearch {
	
	private Date dateFrom;
	
	private Date dateTo;
	
	private Long customerId;
	
	private Boolean paidStatus;
	
	public DateSearch(){
	}

	public Date getDateFrom() {
		return dateFrom;
	}

	public void setDateFrom(Date dateFrom) {
		this.dateFrom = dateFrom;
	}

	public Date getDateTo() {
		return dateTo;
	}

	public void setDateTo(Date dateTo) {
		this.dateTo = dateTo;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public Boolean getPaidStatus() {
		return paidStatus;
	}

	public void setPaidStatus(Boolean paidStatus) {
		this.paidStatus = paidStatus;
	}
}
