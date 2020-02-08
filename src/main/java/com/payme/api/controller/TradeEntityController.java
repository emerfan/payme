package com.payme.api.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.payme.api.dao.TradeEntityRepository;
import com.payme.api.model.TradeEntity;

/**
 * The CustomerController class Provides REST endpoints for Customer related
 * operations
 * 
 * @author emerfanning
 *
 */
@RestController
@RequestMapping("/rest/tradeEntity")
public class TradeEntityController {

	@Autowired
	private TradeEntityRepository tradeEntityDao;

	/**
	 * 
	 * @return
	 */
	@GetMapping()
	public Iterable<TradeEntity> all() {
		return tradeEntityDao.findAll();
	}

	/**
	 * 
	 * @param newTradeEntity
	 * @return
	 */
	@PostMapping()
	public TradeEntity saveTradeEntity(@RequestBody TradeEntity newTradeEntity) {
		return tradeEntityDao.saveAndFlush(newTradeEntity);
	}

	/**
	 * 
	 * @param id
	 */
	@DeleteMapping("/delete/{id}")
	public void deleteTradeEntity(@PathVariable long id) {
		tradeEntityDao.deleteById(id);
	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("/{id}")
	public Optional<TradeEntity> findById(@PathVariable("id") final Long id) {
		return tradeEntityDao.findById(id);
	}

	/**
	 * 
	 * @param updatedTradeEntity
	 * @param id
	 * @return
	 */
	@PutMapping("/{id}")
	public TradeEntity updateTradeEntity(@RequestBody TradeEntity updatedTradeEntity, @PathVariable Long id) {
		return tradeEntityDao.save(updatedTradeEntity);
	}
}
