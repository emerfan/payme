package com.emer.api.validation;

import org.springframework.stereotype.Component;

import com.emer.api.exception.InvalidRequestException;
import com.emer.api.model.InvoiceItem;
import com.emer.api.model.NewInvoiceRequest;
import com.emer.api.utils.InvoiceConstants;

@Component
public class InvoiceValidator {
//	
//	/**
//	 * 
//	 * @param newInvoiceRequest
//	 * @throws InvalidRequestException
//	 */
//	public void validateNewInvoiceRequest(NewInvoiceRequest newInvoiceRequest) throws InvalidRequestException {
//		if (newInvoiceRequest.getNewInvoice() == null) {
//			throw new InvalidRequestException(InvoiceConstants.INVOICE_CANNOT_BE_NULL_EXCEPTION_MESSAGE);
//		}		
//		if(newInvoiceRequest.getNewInvoice().getCustomerId() == null) {
//			throw new InvalidRequestException(InvoiceConstants.INVOICE_CUSTOMER_ID_CANNOT_BE_NULL_EXCEPTION_MESSAGE);
//		}
//		if (!newInvoiceRequest.isCalculateInvoice()) {
//			if (newInvoiceRequest.getNewInvoice().getTotalExVat() == null) {
//				throw new InvalidRequestException(
//						String.format(InvoiceConstants.CANNOT_BE_NULL_IF_INVOICE_IS_CALCULATED_EXCEPTION_MESSAGE, 
//								InvoiceConstants.INVOICE_TOTAL_EX_VAT));
//			}
//			if (newInvoiceRequest.getNewInvoice().getVat() == null) {
//				throw new InvalidRequestException(
//						String.format(InvoiceConstants.CANNOT_BE_NULL_IF_INVOICE_IS_CALCULATED_EXCEPTION_MESSAGE, 
//								InvoiceConstants.INVOICE_VAT));
//			}
//			if (newInvoiceRequest.getNewInvoice().getTotal() == null) {
//				throw new InvalidRequestException(
//						String.format(InvoiceConstants.CANNOT_BE_NULL_IF_INVOICE_IS_CALCULATED_EXCEPTION_MESSAGE, 
//								InvoiceConstants.INVOICE_TOTAL));
//			}
//		}
//		
//		if (!newInvoiceRequest.isCalculateInvoice()) {				
//			if(newInvoiceRequest.getNewInvoice().getItems() == null || 
//					newInvoiceRequest.getNewInvoice().getItems().isEmpty()) {
//				throw new InvalidRequestException(
//						InvoiceConstants.ITEMS_CANNOT_BE_NULL_IF_INVOICE_IS_NOT_CALCULATED_EXCEPTION_MESSAGE);			
//			}			
//			for(InvoiceItem item: newInvoiceRequest.getNewInvoice().getItems()) {
//				if(item.getPrice() == null) {
//					throw new InvalidRequestException(
//							InvoiceConstants.ITEM_MUST_HAVE_PRICE_EXCEPTION_MESSAGE);
//				}
//				if(item.getQty() == null) {
//					throw new InvalidRequestException(
//							InvoiceConstants.ITEM_MUST_HAVE_QTY_EXCEPTION_MESSAGE);
//				}
//			}		
//		}		
//	}
}
