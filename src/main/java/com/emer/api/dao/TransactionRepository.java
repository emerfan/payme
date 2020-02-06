package com.emer.api.dao;

import java.util.Date;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.emer.api.model.FinancialReport;
import com.emer.api.model.Transaction;
import com.emer.api.model.TransactionType;

@Repository
@Transactional
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

	Optional<Transaction> findById(Long id);

	// TODO Implement pagination
	Iterable<Transaction> findByTradeEntityId(Long id);

	Iterable<Transaction> findByIsPaidAndTransactionType(Boolean isPaid, TransactionType type);

	Iterable<Transaction> findByTransactionDate(Date invoiceDate);

	Iterable<Transaction> findByTransactionDateBetween(Date start, Date end);

	Iterable<Transaction> findByTradeEntityIdAndTransactionDateBetween(Long tradeEntityId, Date start, Date end);

	Iterable<Transaction> findByIsPaidAndTransactionTypeAndTransactionDateBetween(Boolean isPaid, TransactionType type,
			Date start, Date end);

	Iterable<Transaction> findByTradeEntityIdAndIsPaidAndTransactionTypeAndTransactionDateBetween(Long tradeEntityId,
			Boolean isPaid, TransactionType type, Date start, Date end);

	@Query("SELECT MAX(id) FROM Transaction")
	Long getLastId();

	@Query(value = "SELECT SUM(vat) as transactionVat, SUM(total_ex_vat) as transactionTotalExVat"
			+ ", SUM(total) as transactionTotal FROM transaction WHERE "
			+ "transaction_type = ?1 transaction_date BETWEEN ?2 AND ?3", nativeQuery = true)
	FinancialReport getReport(Date invoiceDateStart, Date invoiceDateEnd, TransactionType type);
}
