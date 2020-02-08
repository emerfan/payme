package com.payme.api.service;

import java.util.List;
import java.util.Optional;

import com.payme.api.model.DateSearch;
import com.payme.api.model.Transaction;

public interface TransactionService {

	Optional<Transaction> findOne(Long id);

	void delete(Long id);

	Transaction save(Transaction transaction, boolean isSendMail);

	List<Transaction> search(DateSearch params);

	// Workaround for MySQL DB, does not support Hibernate sequence
	void setId(Transaction transaction);
}
