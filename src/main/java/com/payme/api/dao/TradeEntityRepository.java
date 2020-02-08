package com.payme.api.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.payme.api.model.TradeEntity;

/**
 * @author emerfanning
 * 
 *         Jpa Repository for Customers
 *
 */
@Repository
@Transactional
public interface TradeEntityRepository extends JpaRepository<TradeEntity, Long> {

	Optional<TradeEntity> findByName(String name);

}
