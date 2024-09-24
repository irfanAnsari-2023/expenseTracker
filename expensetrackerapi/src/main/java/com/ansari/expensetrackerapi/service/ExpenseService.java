package com.ansari.expensetrackerapi.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ansari.expensetrackerapi.entity.Expense;

public interface ExpenseService {
	
	Page<Expense> getAllExpense(Pageable pageable);
	
	Expense getExpneseById(Long id);

	void deleteExpenseById(Long id);
	
	Expense saveExpenseDetails(Expense expense);
	
	Expense updateExpenseDetails(Long id, Expense expense);
	
	
}
