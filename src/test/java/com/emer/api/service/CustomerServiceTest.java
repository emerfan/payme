package com.emer.api.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;

import com.emer.api.dao.CustomerRepository;
import com.emer.api.model.Customer;

@RunWith(MockitoJUnitRunner.class)
public class CustomerServiceTest {

	@Mock
	private CustomerRepository repo;

	private Customer mockCustomer;

	private Optional<Customer> customerResult;

	private CustomerService customerService;

	@Before
	public void setUp() {
		mockCustomer = new Customer();
		mockCustomer.setId(1l);
		mockCustomer.setSalonName("Fake Salon");
		mockCustomer.setEmail("fakesalon@gmail.com");

		this.customerResult = Optional.of(mockCustomer);
		this.customerService = new CustomerServiceImpl();

		ReflectionTestUtils.setField(customerService, "customerDao", repo);
	}

	@Test
	public void findByCustomerIdTest() {
		// Arrange
		when(repo.findById(1l)).thenReturn(customerResult);

		// Act
		Optional<Customer> result = this.customerService.findByCustomerId(1l);

		// Assert
		assertEquals(customerResult, result);
	}
}
