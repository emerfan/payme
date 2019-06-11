package com.emer.api.controllers;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.emer.api.model.Customer;
import com.emer.api.service.CustomerService;
/**
 * The CustomerController class
 * Provides REST endpoints for Customer related operations
 * 
 * @author emerfanning
 *
 */
@RestController
@RequestMapping("/rest/customer")
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	/**
	 * 
	 * @return
	 */
	@GetMapping()
	public Iterable<Customer> all() {
		return customerService.all();
	}
	
	/**
	 * 
	 * @param newCustomer
	 * @return
	 */
	@PostMapping()
	public Customer addCustomer(@RequestBody Customer newCustomer) {
		return customerService.addCustomer(newCustomer);
	}
	
	/**
	 * 
	 * @param id
	 */
	@DeleteMapping("/delete/{id}")
	public void deleteCustomer(@PathVariable long id) {
		customerService.deleteCustomer(id);
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("/{id}")
	public Optional<Customer> findByCustomerId(@PathVariable("id") final Long id) {
		return customerService.findByCustomerId(id);
	}
	
	/**
	 * 
	 * @param updatedCustomer
	 * @param id
	 * @return
	 */
	@PutMapping("/{id}")
	public Customer updateCustomer(@RequestBody Customer updatedCustomer, @PathVariable Long id){
		return updateCustomer(updatedCustomer, id);
	}
}
