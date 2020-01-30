package com.emer.api.dao;
import java.util.Date;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.emer.api.model.Expense;
import com.emer.api.model.FinancialReport;

/**
 * @author emerfanning
 * Jpa Repository for Expenses
 *
 */
@Repository
@Transactional
public interface ExpenseRepository extends JpaRepository<Expense, Long> {
	
	/**
	 * The findByIds method. Return the Expense
	 * matching the id passed.
	 */
	Optional<Expense> findById(Long id);
	
	/**
	 * The findByIsPaid method. Retrieve all Expenses
	 * matching the paid status passed.
	 * 
	 * @param isPaid
	 * @return
	 */
	Iterable<Expense> findByIsPaid(Boolean isPaid);
	
	/**
	 * The findByExpenseDate method.
	 * 
	 * Retrieve all Expenses with a date matching the date passed.
	 * 
	 * @param ExpenseDate
	 * @return
	 */
	Iterable<Expense> findByExpenseDate(Date expenseDate);
	
	/**
	 * The findByExpenseDateBetween method. Retrieve all
	 * Expenses between the dates passed.
	 * 
	 * @param ExpenseDateStart
	 * @param ExpenseDateEnd
	 * @return
	 */
	Iterable<Expense> findByExpenseDateBetween(Date expenseDateStart, Date expenseDateEnd);
	
	/**
	 * The findByCustomerIdAndExpenseDateBetween method. Find by customer id
	 * and between the dates passed.
	 * 
	 * @param customerId
	 * @param ExpenseDateStart
	 * @param ExpenseDateEnd
	 * @return
	 */
	Iterable<Expense> findBySupplierIdAndExpenseDateBetween(
			Long supplierId,
			Date expenseDateStart, 
			Date expenseDateEnd);
	
	/**
	 * The findByIsPaidAndExpenseDateBetween method. Find all Expenses
	 * with the status passed and between the dates passed.
	 * 
	 * @param isPaid
	 * @param ExpenseDateStart
	 * @param ExpenseDateEnd
	 * @return
	 */
	Iterable<Expense> findByIsPaidAndExpenseDateBetween(
			Boolean isPaid,
			Date expenseDateStart, 
			Date expenseDateEnd);
	
	/**
	 * The findByCustomerIdAndIsPaidAndExpenseDateBetween method
	 * Search by customer id, paid status and between the dates passed.
	 * 
	 * @param customerId
	 * @param isPaid
	 * @param ExpenseDateStart
	 * @param ExpenseDateEnd
	 * @return
	 */
	Iterable<Expense> findBySupplierIdAndIsPaidAndExpenseDateBetween(
			Long supplierId,
			Boolean isPaid,
			Date expenseDateStart, 
			Date expenseDateEnd);
	/**
	 * The getLastId for Expense method. Used to retrieve the last id in the DB in
	 * order to set the next one
	 * 
	 * @return
	 */
	@Query("SELECT MAX(id) FROM Expense")
	Long getLastId();
	

	/**
	 * The getSalesReport method. Returns the SUM of the sales vat, exvat and total
	 * for the given dates
	 * 
	 * @return
	 */
	@Query(value = "SELECT SUM(vat) as expenseVat, SUM(total_ex_vat) as expenseTotalExVat"
			+ ", SUM(total) as expenseTotal FROM expense WHERE expense_date BETWEEN ?1 AND ?2", nativeQuery = true)
	FinancialReport getExpensesReport(Date expenseDateStart, 
			Date expenseDateEnd);
}
