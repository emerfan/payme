package com.emer.api.service;

import java.util.List;
import java.util.Optional;
import com.emer.api.model.DateSearch;
import com.emer.api.model.Expense;
/**
 * 
 * @author emerfanning
 *
 */
public interface ExpenseService {
	
	/**
	 * 
	 * @param dateSearchParams
	 * @return
	 */
	List<Expense> searchExpenses(DateSearch dateSearchParams);
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	Optional<Expense> getExpense(Long id);
	
	/**
	 * 
	 * @param id
	 */
	void deleteExpense(Long id);
	
	/**
	 * 
	 * @param newExpense
	 * @return
	 */
	Expense saveExpense(Expense newExpense);
				
}
