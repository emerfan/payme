package com.emer.api;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.emer.api.calculation.InvoiceCalculator;
import com.emer.api.service.InvoiceService;
import com.emer.api.service.InvoiceServiceImpl;
import com.emer.api.utils.InvoiceMailer;
import com.emer.api.utils.InvoicePdfBuilder;

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
