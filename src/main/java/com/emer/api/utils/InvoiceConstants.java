package com.emer.api.utils;

public class InvoiceConstants {
	
	public static String INVOICE_START = "INV";
	
	public static String INVOICE_SEPARATOR = "-";
	
	public static String INVOICE_CANNOT_BE_NULL_EXCEPTION_MESSAGE ="Invoice cannot be null.";
	
	public static String INVOICE_CUSTOMER_ID_CANNOT_BE_NULL_EXCEPTION_MESSAGE ="Invoice Customer ID cannot be null.";
	
	public static String INVOICE_ID ="Invoice ID";
	
	public static String INVOICE_VAT ="Invoice VAT";
	
	public static String INVOICE_TOTAL_EX_VAT ="Invoice Total Ex VAT";
	
	public static String INVOICE_TOTAL ="Invoice Total";
	
	public static String CANNOT_BE_NULL_IF_INVOICE_IS_CALCULATED_EXCEPTION_MESSAGE 
	= "%s cannot be null if invoice is calculated.";
	
	public static String ITEMS_CANNOT_BE_NULL_IF_INVOICE_IS_NOT_CALCULATED_EXCEPTION_MESSAGE 
	= "Invoice must contain items to be calculated.";
	
	public static String ITEM_MUST_HAVE_PRICE_EXCEPTION_MESSAGE = "Each item must have a price for calculation";
	
	public static String ITEM_MUST_HAVE_QTY_EXCEPTION_MESSAGE = "Each item must have a quantity for calculation";
}
