package com.emer.api.service;

import java.util.Optional;

import com.emer.api.model.Customer;

/**
 * 
 * @author emerfanning
 *
 */
public interface CustomerService {
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	Optional<Customer> findByCustomerId(Long id);
	
	/**
	 * 
	 * @param newCustomer
	 * @return
	 */
	Customer addCustomer(Customer newCustomer);
	
	/**
	 * 
	 * @param id
	 */
	void deleteCustomer(long id);
	
	/**
	 * 
	 * @return
	 */
	Iterable<Customer> all();
	
	/**
	 * 
	 * @param updatedCustomer
	 * @param id
	 * @return
	 */
	Customer updateCustomer(Customer updatedCustomer, Long id);
}
