package com.emer.api.service;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.emer.api.dao.ExpenseRepository;
import com.emer.api.model.DateSearch;
import com.emer.api.model.Expense;

@Service
public class ExpenseServiceImpl implements ExpenseService {

	@Autowired
	private ExpenseRepository expenseDao;
	
	@Override
	public Optional<Expense> getExpense(Long id) {
		return expenseDao.findById(id);
	}
	@Override
	public void deleteExpense(Long id) {
		expenseDao.deleteById(id);
	}
	
	@Override
	public Expense saveExpense(Expense newExpense) {
		if(Objects.isNull(newExpense.getId())) {
			this.setExpenseId(newExpense);
		}
		
		return expenseDao.save(newExpense);
	}
	
	@Override
	public List<Expense> searchExpenses(DateSearch dateSearchParams) {
		if (isFullSearch(dateSearchParams)) {
			return (List<Expense>) expenseDao
					.findBySupplierIdAndIsPaidAndExpenseDateBetween(dateSearchParams.getCustomerId(),
							dateSearchParams.getPaidStatus(), dateSearchParams.getDateFrom(), 
							dateSearchParams.getDateTo());
			}
		if(isSearchTimePeriodForStatus(dateSearchParams)) {
			return (List<Expense>) expenseDao
			.findByIsPaidAndExpenseDateBetween(dateSearchParams.getPaidStatus(), dateSearchParams.getDateFrom(), 
							dateSearchParams.getDateTo());

		}
		if(isSearchTimePeriodForCustomer(dateSearchParams)) {
			return (List<Expense>) expenseDao 
					.findBySupplierIdAndExpenseDateBetween(dateSearchParams.getCustomerId(), dateSearchParams.getDateFrom(), 
							dateSearchParams.getDateTo());

		}
		if(isSearchTimePeriod(dateSearchParams)) {
			return (List<Expense>) expenseDao 
					.findByExpenseDateBetween(dateSearchParams.getDateFrom(), dateSearchParams.getDateTo());
		}
		return null;
	}
	
	/**
	 * 
	 * @param newInvoice
	 */
	private void setExpenseId(Expense newExpense) {
		Long nextId = expenseDao.getLastId();
		Long currentId = nextId+1;	
		newExpense.setId(currentId);
	}
	
	/**
	 * 
	 * @param dateSearchParams
	 * @return
	 */
	private boolean isFullSearch(DateSearch dateSearchParams) {
		return dateSearchParams.getPaidStatus() != null &&
				dateSearchParams.getCustomerId() != null && dateSearchParams.getDateFrom() != null
				&& dateSearchParams.getDateTo() != null;
	}
	
	/**
	 * 
	 * @param dateSearchParams
	 * @return
	 */
	private boolean isSearchTimePeriodForStatus(DateSearch dateSearchParams) {
		return dateSearchParams.getPaidStatus() != null &&
				dateSearchParams.getDateFrom() != null
				&& dateSearchParams.getDateTo() != null;
	}
	
	/**
	 * 
	 * @param dateSearchParams
	 * @return
	 */
	private boolean isSearchTimePeriodForCustomer(DateSearch dateSearchParams) {
		return dateSearchParams.getCustomerId() != null &&
				dateSearchParams.getDateFrom() != null
				&& dateSearchParams.getDateTo() != null;
	}
	
	/**
	 * 
	 * @param dateSearchParams
	 * @return
	 */
	private boolean isSearchTimePeriod(DateSearch dateSearchParams) {
		return dateSearchParams.getDateFrom() != null
				&& dateSearchParams.getDateTo() != null;
	}
}
