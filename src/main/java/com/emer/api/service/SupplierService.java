package com.emer.api.service;

import java.util.Optional;

import com.emer.api.model.Supplier;

/**
 * 
 * @author emerfanning
 *
 */
public interface SupplierService {
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	Optional<Supplier> findBySupplierId(Long id);
	
	/**
	 * 
	 * @param newCustomer
	 * @return
	 */
	Supplier addSupplier(Supplier newSupplier);
	
	/**
	 * 
	 * @param id
	 */
	void deleteSupplier(long id);
	
	/**
	 * 
	 * @return
	 */
	Iterable<Supplier> all();
	
	/**
	 * 
	 * @param updatedCustomer
	 * @param id
	 * @return
	 */
	Supplier updateCustomer(Supplier updatedSupplier, Long id);
}
