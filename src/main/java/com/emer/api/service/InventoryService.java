package com.emer.api.service;

import java.util.Optional;

import com.emer.api.model.InventoryItem;

/**
 * 
 * @author emerfanning
 *
 */
public interface InventoryService {

	Iterable<InventoryItem> findAll();
	
	Optional<InventoryItem> findOne(Long id);
	
	InventoryItem save(InventoryItem newInventoryItem);
	
	void delete(Long id);	
}
