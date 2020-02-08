package com.payme.api.model;

public enum InventoryStatus {

	IN_STOCK("InStock"), OUT_OF_STOCK("Out_Of_Stock");

	private String status;

	InventoryStatus(String type) {
		this.status = type;
	}

	public String getStatus() {
		return status;
	}
}
