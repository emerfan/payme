package com.emer.api;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.payme.api.service.InvoiceService;
import com.payme.api.service.InvoiceServiceImpl;
import com.payme.api.utils.InvoiceCalculator;
import com.payme.api.utils.InvoiceMailer;
import com.payme.api.utils.InvoicePdfBuilder;

@Configuration
public class TestConfig {

	@Bean
	InvoiceService invoiceService() {
		return new InvoiceServiceImpl(invoicePdfBuilder(), invoiceMailer(), invoiceCalculator());
	}

	InvoiceCalculator invoiceCalculator() {
		return new InvoiceCalculator();
	}

	InvoicePdfBuilder invoicePdfBuilder() {
		return new InvoicePdfBuilder();
	}

	InvoiceMailer invoiceMailer() {
		return new InvoiceMailer();
	}
}
