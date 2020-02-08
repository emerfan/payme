package com.payme.api.service;

import java.util.Optional;

import com.payme.api.model.InventoryItem;

public interface InventoryService {

	Iterable<InventoryItem> findAll();
	
	Optional<InventoryItem> findOne(Long id);
	
	InventoryItem save(InventoryItem newInventoryItem);
	
	void delete(Long id);	
}
