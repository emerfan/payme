package com.emer.api.dao;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.emer.api.model.Supplier;

/**
 * @author emerfanning
 * 
 * Jpa Repository for Suppliers
 *
 */
@Repository
@Transactional
public interface SupplierRepository extends JpaRepository<Supplier, Long> {
	
	Iterable<Supplier> findByName(String name);
	
	Optional<Supplier> findById(Long id);

}
