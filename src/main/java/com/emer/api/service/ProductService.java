package com.emer.api.service;

import java.util.Optional;

import com.emer.api.model.Product;

/**
 * 
 * @author emerfanning
 *
 */
public interface ProductService {
	
	/**
	 * 
	 * @return
	 */
	Iterable<Product> findAll();
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	Optional<Product> findOne(Long id);
	
	/**
	 * 
	 * @param newProduct
	 * @return
	 */
	Product saveProduct(Product newProduct);
	
	/**
	 * 
	 * @param updateProduct
	 * @return
	 */
	Product updateProduct(Product updateProduct);
	
	/**
	 * 
	 * @param deleteProduct
	 */
	void deleteProduct(Product deleteProduct);
	
	/**
	 * 
	 * @param newProduct
	 */
	void setProductId(Product newProduct);	
}
