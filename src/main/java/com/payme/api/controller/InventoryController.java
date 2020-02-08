package com.payme.api.controller;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.payme.api.model.InventoryItem;
import com.payme.api.service.InventoryService;
@RestController
@RequestMapping("/rest/inventory")
public class InventoryController {
	
	@Autowired
	private InventoryService inventoryService;
	
	/**
	 * 
	 * @return
	 */
	@GetMapping()
	public Iterable<InventoryItem> findAll() {
		return inventoryService.findAll();
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("/{id}")
	public Optional<InventoryItem> findOne(@PathVariable("id")Long id) {
		return inventoryService.findOne(id);
	}
	
	/**
	 * 
	 * @param newProduct
	 * @return
	 */
	@PostMapping()
	public InventoryItem saveProduct(@RequestBody InventoryItem newProduct) {
		return inventoryService.save(newProduct);
	}
	
	/**
	 * 
	 * @param deleteProduct
	 */
	@DeleteMapping("/delete/{id}")
	public void deleteProduct(@PathVariable("id")Long id) {
		inventoryService.delete(id);
	}
}
