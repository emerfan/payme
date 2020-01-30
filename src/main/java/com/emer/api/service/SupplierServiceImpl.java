package com.emer.api.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.emer.api.dao.SupplierRepository;
import com.emer.api.model.Supplier;
@Component
public class SupplierServiceImpl implements SupplierService {
	
	@Autowired
	private SupplierRepository supplierDao;
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public Optional<Supplier> findBySupplierId(Long id) {
		return supplierDao.findById(id);
	}
	
	/**
	 * 
	 * @param newCustomer
	 * @return
	 */
	@Override
	public Supplier addSupplier(Supplier newSupplier) {
		return supplierDao.save(newSupplier);
	}
	
	/**
	 * 
	 * @param id
	 */
	@Override
	public void deleteSupplier(long id) {
		supplierDao.deleteById(id);
	}
	
	/**
	 * 
	 * @return
	 */
	@Override
	public Iterable<Supplier> all() {
		return supplierDao.findAll();
	}
	
	/**
	 * 
	 * @param updatedCustomer
	 * @param id
	 * @return
	 */
	@Override
	public Supplier updateCustomer(Supplier newSupplier, Long id) {
		Optional<Supplier> existingSupplier = supplierDao.findById(id);
		if(existingSupplier.isPresent()){
			//TODO
			//Implement Update Supplier
		} 
		//return supplierDao.save(updatedSupplier);	
		return null;
	}
}
