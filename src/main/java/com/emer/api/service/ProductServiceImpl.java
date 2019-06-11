package com.emer.api.service;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emer.api.dao.ProductRepository;
import com.emer.api.model.Product;

@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	private ProductRepository productDao;
	
	/**
	 * 
	 * @return
	 */
	@Override
	public Iterable<Product> findAll() {
		return productDao.findAll();
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public Optional<Product> findOne(Long id) {
		return productDao.findById(id);
	}
	
	/**
	 * 
	 * @param newProduct
	 * @return
	 */
	@Override
	public Product saveProduct(Product newProduct) {
		setProductId(newProduct);
		return productDao.save(newProduct);
	}
	
	/**
	 * 
	 * @param updateProduct
	 * @return
	 */
	@Override
	public Product updateProduct(Product updateProduct) {
		return productDao.save(updateProduct);
	}
	
	/**
	 * 
	 * @param deleteProduct
	 */
	@Override
	public void deleteProduct(Product deleteProduct) {
		productDao.delete(deleteProduct);
	}

	/**
	 * 
	 * @param newProduct
	 */
	@Override
	public void setProductId(Product newProduct) {
		Long id = productDao.getLastId();
		Long currentId = id + 1;
		newProduct.setId(currentId);
	}
}
