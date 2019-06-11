package com.emer.api.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.emer.api.dao.CustomerRepository;
import com.emer.api.model.Customer;
@Component
public class CustomerServiceImpl implements CustomerService {
	
	@Autowired
	private CustomerRepository customerDao;
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public Optional<Customer> findByCustomerId(Long id) {
		return customerDao.findById(id);
	}
	
	/**
	 * 
	 * @param newCustomer
	 * @return
	 */
	@Override
	public Customer addCustomer(Customer newCustomer) {
		return customerDao.save(newCustomer);
	}
	
	/**
	 * 
	 * @param id
	 */
	@Override
	public void deleteCustomer(long id) {
		customerDao.deleteById(id);
	}
	
	/**
	 * 
	 * @return
	 */
	@Override
	public Iterable<Customer> all() {
		return customerDao.findAll();
	}
	
	/**
	 * 
	 * @param updatedCustomer
	 * @param id
	 * @return
	 */
	@Override
	public Customer updateCustomer(Customer updatedCustomer, Long id) {
		Optional<Customer> existingCustomer = customerDao.findById(id);
		if(existingCustomer.isPresent()){
			//TODO
			//Implement Update Customer
		} 
		//return customerDao.save(updatedCustomer);	
		return null;
	}
}
