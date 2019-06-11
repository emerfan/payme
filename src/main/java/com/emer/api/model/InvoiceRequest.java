package com.emer.api.model;

public class InvoiceRequest {
	
	private Invoice newInvoice;
	
	private boolean sendMail;
	
	public InvoiceRequest(){
		
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
		return sendMail;
	}

	/**
	 * 
	 * @param sendMail
	 */
	public void setSendMail(boolean sendMail) {
		this.sendMail = sendMail;
	}
}
