package com.emer.api.model;

public class NewInvoiceRequest {
	
	private Invoice newInvoice;
	
	private boolean isSendMail;
	
	private boolean isCalculateInvoice;
	
	public NewInvoiceRequest(){		
	}
	
	/**
	 * 
	 * @return
	 */
	public Invoice getNewInvoice() {
		return newInvoice;
	}
	
	/**
	 * 
	 * @param newInvoice
	 */
	public void setNewInvoice(Invoice newInvoice) {
		this.newInvoice = newInvoice;
	}
	
	/**
	 * 
	 * @return
	 */
	public boolean isSendMail() {
		return isSendMail;
	}

	/**
	 * 
	 * @param sendMail
	 */
	public void setIsSendMail(boolean isSendMail) {
		this.isSendMail = isSendMail;
	}
	
	/**
	 * 
	 * @return
	 */
	public boolean isCalculateInvoice() {
		return isCalculateInvoice;
	}
	
	/**
	 * 
	 * @param isCalculateInvoice
	 */
	public void setCalculateInvoice(boolean isCalculateInvoice) {
		this.isCalculateInvoice = isCalculateInvoice;
	}	
}
