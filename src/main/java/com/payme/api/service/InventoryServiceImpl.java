package com.payme.api.service;
import java.util.Optional;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Service;

import com.payme.api.dao.InventoryRepository;
import com.payme.api.model.InventoryItem;

@Service
public class InventoryServiceImpl implements InventoryService {
	
	private InventoryRepository inventoryRepository;
	
	public InventoryServiceImpl(InventoryRepository inventoryRepository) {
		this.inventoryRepository = inventoryRepository;			
	}
	
	@Override
	public Iterable<InventoryItem> findAll() {
		Iterable<InventoryItem> items = this.inventoryRepository.findAll();
		StreamSupport.stream(items.spliterator(), false)
		.forEach(item -> item.updateStatus());
		
		return items;
	}

	@Override
	public Optional<InventoryItem> findOne(Long id) {
		return inventoryRepository.findById(id);
	}

	@Override
	public InventoryItem save(InventoryItem newInventoryItem) {
		return inventoryRepository.saveAndFlush(newInventoryItem);
	}

	@Override
	public void delete(Long id) {
		inventoryRepository.deleteById(id);	
	}	
}
