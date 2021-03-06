package com.payme.api.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.ImmutableList;
import com.itextpdf.text.DocumentException;
import com.payme.api.dao.TradeEntityRepository;
import com.payme.api.dao.TransactionRepository;
import com.payme.api.model.DateSearch;
import com.payme.api.model.TradeEntity;
import com.payme.api.model.TradeEntityType;
import com.payme.api.model.Transaction;
import com.payme.api.model.TransactionItem;
import com.payme.api.model.TransactionType;
import com.payme.api.utils.SearchUtility;

@Service
public class TransactionServiceImpl implements TransactionService {

	private InvoiceService invoiceService;

	@Autowired
	private TradeEntityRepository tradeEntityDao;

	@Autowired
	private TransactionRepository transactionDao;

	/**
	 * 
	 * @param invoiceService
	 * @param tradeEntityDao
	 * @param transactionDao
	 */
	public TransactionServiceImpl(InvoiceService invoiceService) {
		this.invoiceService = invoiceService;
	}

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

		return this.transactionDao.saveAndFlush(transaction);
	}

	@Override
	public void setId(Transaction transaction) {
		Long lastId = this.transactionDao.getLastId();
		if(lastId != null) {
			transaction.setId(lastId + 1);
		}
		else {
			transaction.setId(1l);
		}
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
		Optional<TradeEntity> tradeEntity =  this.tradeEntityDao.findById(transaction.getTradeEntityId());
		
		if(tradeEntity.isPresent()) {
			transaction.setTradeEntityName(tradeEntity.get().getName());
		} else {
			TradeEntity newTradeEntity = new TradeEntity();
			newTradeEntity.setName(transaction.getTradeEntityName());
			
			if (transaction.getTransactionType() == TransactionType.INVOICE) {
				newTradeEntity.setTradeEntityType(TradeEntityType.DEBTOR);
			} else {
				newTradeEntity.setTradeEntityType(TradeEntityType.CREDITOR);
			}
			
			this.tradeEntityDao.save(newTradeEntity);
		}	
	}
}
