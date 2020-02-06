package com.emer.api.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.emer.api.model.TradeEntity;

/**
 * @author emerfanning
 * 
 *         Jpa Repository for Customers
 *
 */
@Repository
@Transactional
public interface TradeEntityRepository extends JpaRepository<TradeEntity, Long> {

	Iterable<TradeEntity> findByName(String name);

}
