package com.payme.api.dao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.payme.api.model.InventoryItem;

@Repository
@Transactional
public interface InventoryRepository extends JpaRepository<InventoryItem, Long> {	
}
