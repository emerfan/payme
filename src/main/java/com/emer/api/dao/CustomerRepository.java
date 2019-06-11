package com.emer.api.dao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.emer.api.model.Customer;

/**
 * @author emerfanning
 * 
 * Jpa Repository for Customers
 *
 */
@Repository
@Transactional
public interface CustomerRepository extends JpaRepository<Customer, Long> {
	
	Iterable<Customer> findBySalonName(String salonName);

}
