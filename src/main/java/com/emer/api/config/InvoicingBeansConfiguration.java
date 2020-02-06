package com.emer.api.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.emer.api.calculation.InvoiceCalculator;
import com.emer.api.service.InvoiceService;
import com.emer.api.service.InvoiceServiceImpl;
import com.emer.api.utils.InvoiceMailer;
import com.emer.api.utils.InvoicePdfBuilder;

/**
 * This class contains the beans required by the invoicing app
 * 
 * @author emerfanning
 *
 */
@Configuration
public class InvoicingBeansConfiguration {

	/**
	 * The Rest Template Bean
	 * 
	 * @return
	 */
	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}

	/**
	 * CORS Configuration
	 * 
	 * @return
	 */
	@Bean
	public FilterRegistrationBean<CorsFilter> corsFilter() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowCredentials(true);
		config.addAllowedOrigin("*");
		config.addAllowedHeader("*");
		config.addAllowedMethod("OPTIONS");
		config.addAllowedMethod("HEAD");
		config.addAllowedMethod("GET");
		config.addAllowedMethod("PUT");
		config.addAllowedMethod("POST");
		config.addAllowedMethod("DELETE");
		config.addAllowedMethod("PATCH");
		source.registerCorsConfiguration("/**", config);
		final FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<CorsFilter>(new CorsFilter(source));
		bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
		return bean;
	}

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
