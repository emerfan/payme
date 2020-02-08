package com.emer.api.model;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MapKeyColumn;

@Entity
public class InventoryItem {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal cost;

    private InventoryStatus inventoryStatus;
    
    private int quantityInStock;
    
    private String name;
    
    private String description;
    
    private boolean isManageStock;
    
    @CollectionTable
    private HashMap<String, Set<String>> attributes;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getCost() {
		return cost;
	}

	public void setCost(BigDecimal cost) {
		this.cost = cost;
	}

	public InventoryStatus getInventoryStatus() {
		return inventoryStatus;
	}

	public void setInventoryStatus(InventoryStatus inventoryStatus) {
		this.inventoryStatus = inventoryStatus;
	}

	public int getQuantityInStock() {
		return quantityInStock;
	}

	public void setQuantityInStock(int quantityInStock) {
		this.quantityInStock = quantityInStock;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isManageStock() {
		return isManageStock;
	}

	public void setManageStock(boolean isManageStock) {
		this.isManageStock = isManageStock;
	}

	public HashMap<String, Set<String>> getAttributes() {
		return attributes;
	}

	public void setAttributes(HashMap<String, Set<String>> attributes) {
		this.attributes = attributes;
	}

	public void updateStatus() {
		this.setInventoryStatus(InventoryStatus.IN_STOCK);
		
		if(isManageStock) {
			if(quantityInStock <= 0) {
				this.setInventoryStatus(InventoryStatus.OUT_OF_STOCK);
			}
		}
	}
}
