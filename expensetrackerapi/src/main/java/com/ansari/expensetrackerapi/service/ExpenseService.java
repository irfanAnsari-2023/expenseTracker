package com.ansari.expensetrackerapi.service;

import java.sql.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ansari.expensetrackerapi.entity.Expense;

public interface ExpenseService {
	
	Page<Expense> getAllExpense(Pageable pageable);
	
	Expense getExpneseById(Long id);

	void deleteExpenseById(Long id);
	
	Expense saveExpenseDetails(Expense expense);
	
	Expense updateExpenseDetails(Long id, Expense expense);
	
	List<Expense> readByCategory(String category, Pageable pageable);
	
	List<Expense> readByName(String name, Pageable pageable);
	
	List<Expense> readByDate(Date startDate, Date endDate);
	
	void deleteAllExpenses();
	
	
}
