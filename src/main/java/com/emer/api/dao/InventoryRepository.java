package com.emer.api.dao;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.emer.api.model.InventoryItem;
import java.util.Optional;

/**
 * @author emerfanning
 * Jpa Repository for Products
 *
 */
@Repository
@Transactional
public interface InventoryRepository extends JpaRepository<InventoryItem, Long> {
	
//	
//	/**
//	 * The getLastId for Product method. Used to retrieve the last id in the DB in
//	 * order to set the next one
//	 * 
//	 * @return
//	 */
//	@Query("SELECT MAX(id) FROM InventoryItem")
//	Long getLastId();	
}
