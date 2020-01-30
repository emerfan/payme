package com.emer.api.controllers;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.emer.api.model.DateSearch;
import com.emer.api.model.Expense;
import com.emer.api.service.ExpenseService;

@RestController
@RequestMapping("/rest/expense")
public class ExpenseController {
	

	@Autowired
	private ExpenseService expenseService;
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("/{id}")
	public Optional<Expense> findById(@PathVariable("id") Long id) {
		return expenseService.getExpense(id);
	}
	
	@PostMapping()
	public Expense addExpense(@RequestBody Expense newExpense) {	
		return expenseService.saveExpense(newExpense);
	}
	
	@DeleteMapping("/{id}")
	public void deleteExpense(@PathVariable Long id){
		expenseService.deleteExpense(id);
	}
	
	@PostMapping("/search")
	public List<Expense> search(@RequestBody DateSearch dateSearchParams) {
		return expenseService.searchExpenses(dateSearchParams);
	}
}
