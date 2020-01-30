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
import com.emer.api.model.Supplier;
import com.emer.api.service.SupplierService;

@RestController
@RequestMapping("/rest/supplier")
public class SupplierController {
	
	@Autowired
	private SupplierService supplierService;
	
	/**
	 * 
	 * @return
	 */
	@GetMapping()
	public Iterable<Supplier> all() {
		return supplierService.all();
	}
	
	/**
	 * 
	 * @param newCustomer
	 * @return
	 */
	@PostMapping()
	public Supplier addSupplier(@RequestBody Supplier newSupplier) {
		return supplierService.addSupplier(newSupplier);
	}
	
	/**
	 * 
	 * @param id
	 */
	@DeleteMapping("/delete/{id}")
	public void deleteSupplier(@PathVariable long id) {
		supplierService.deleteSupplier(id);
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("/{id}")
	public Optional<Supplier> findBySupplierId(@PathVariable("id") final Long id) {
		return supplierService.findBySupplierId(id);
	}
	
	/**
	 * 
	 * @param updatedCustomer
	 * @param id
	 * @return
	 */
	@PutMapping("/{id}")
	public Supplier updateSupplier(@RequestBody Supplier updatedSupplier, @PathVariable Long id){
		return updateSupplier(updatedSupplier, id);
	}
}
