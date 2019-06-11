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
import com.emer.api.model.Product;
import com.emer.api.service.ProductService;
/**
 * The ProductController class
 * Provides REST endpoints for Product related operations
 * 
 * @author emerfanning
 *
 */
@RestController
@RequestMapping("/rest/products")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	/**
	 * 
	 * @return
	 */
	@GetMapping()
	public Iterable<Product> findAll() {
		return productService.findAll();
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("/{id}")
	public Optional<Product> findOne(@PathVariable("id")Long id) {
		return productService.findOne(id);
	}
	
	/**
	 * 
	 * @param newProduct
	 * @return
	 */
	@PostMapping()
	public Product saveProduct(@RequestBody Product newProduct) {
		return productService.saveProduct(newProduct);
	}
	
	/**
	 * 
	 * @param updateProduct
	 * @return
	 */
	@PutMapping("/update")
	public Product updateProduct(@RequestBody Product updateProduct) {
		return productService.updateProduct(updateProduct);
	}
	
	/**
	 * 
	 * @param deleteProduct
	 */
	@DeleteMapping("/delete")
	public void deleteProduct(@RequestBody Product deleteProduct) {
		productService.deleteProduct(deleteProduct);
	}
}
