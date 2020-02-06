package com.emer.api.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emer.api.dao.TradeEntityRepository;
import com.emer.api.dao.TransactionRepository;
import com.emer.api.model.DateSearch;
import com.emer.api.model.Transaction;
import com.emer.api.model.TransactionType;
import com.emer.api.utils.SearchUtility;
import com.google.common.collect.ImmutableList;
import com.itextpdf.text.DocumentException;

@Service
public class TransactionServiceImpl implements TransactionService {

	@Autowired
	private InvoiceServiceImpl invoiceService;

	@Autowired
	private TradeEntityRepository tradeEntityRepository;

	@Autowired
	private TransactionRepository transactionDao;

	@Override
	public Optional<Transaction> findOne(Long id) {
		Optional<Transaction> transaction = this.transactionDao.findById(id);
		if (transaction.isPresent()) {
			this.setTradeEntityName(transaction.get());
		}

		return this.transactionDao.findById(id);
	}

	@Override
	public void delete(Long id) {
		this.transactionDao.deleteById(id);
	}

	@Override
	public Transaction save(Transaction transaction, boolean isSendMail) {
		// ID may be passed as a parameter, e.g. old paper transactions
		if (transaction.getId() == null) {
			this.setId(transaction);
		}

		this.setTradeEntityName(transaction);

		if (transaction.getTransactionType() == TransactionType.INVOICE) {
			transaction = (Transaction) this.invoiceService.calculateInvoice(transaction);
			if (isSendMail) {
				try {
					this.invoiceService.mailInvoice(transaction);
				} catch (IOException | DocumentException | MessagingException e) {
					System.out.println("error mailing invoice");
				}
			}
		}

		return this.transactionDao.save(transaction);
	}

	@Override
	public void setId(Transaction transaction) {
		Long nextId = this.transactionDao.getLastId();
		Long currentId = nextId + 1;
		transaction.setId(currentId);
	}

	@Override
	public List<Transaction> search(DateSearch dateSearchParams) {

		if (SearchUtility.IsFullSearch(dateSearchParams)) {
			return (List<Transaction>) this.transactionDao
					.findByTradeEntityIdAndIsPaidAndTransactionTypeAndTransactionDateBetween(
							dateSearchParams.getTradeEntityId(), dateSearchParams.getPaidStatus(),
							dateSearchParams.getTransactionType(), dateSearchParams.getDateFrom(),
							dateSearchParams.getDateTo());
		}
		if (SearchUtility.IsSearchTimePeriodForStatus(dateSearchParams)) {
			return (List<Transaction>) transactionDao.findByIsPaidAndTransactionTypeAndTransactionDateBetween(
					dateSearchParams.getPaidStatus(), dateSearchParams.getTransactionType(),
					dateSearchParams.getDateFrom(), dateSearchParams.getDateTo());
		}
		if (SearchUtility.IsSearchTimePeriodForTransaction(dateSearchParams)) {
			return (List<Transaction>) transactionDao.findByTradeEntityIdAndTransactionDateBetween(
					dateSearchParams.getTradeEntityId(), dateSearchParams.getDateFrom(), dateSearchParams.getDateTo());
		}
		if (SearchUtility.IsSearchTimePeriod(dateSearchParams)) {
			return (List<Transaction>) transactionDao.findByTransactionDateBetween(dateSearchParams.getDateFrom(),
					dateSearchParams.getDateTo());
		}

		return ImmutableList.of();
	}

	private void setTradeEntityName(Transaction transaction) {
		String name = this.tradeEntityRepository.findById(transaction.getTradeEntityId()).get().getName();
		transaction.setTradeEntityName(name);
	}
}
