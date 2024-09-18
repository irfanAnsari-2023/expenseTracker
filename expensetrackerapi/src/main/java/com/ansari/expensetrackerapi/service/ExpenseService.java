package com.ansari.expensetrackerapi.service;

import java.util.List;

import com.ansari.expensetrackerapi.entity.Expense;

public interface ExpenseService {
	
	List<Expense> getAllExpense();
	
//	Define a mtd which will take the expense id as a param and going to return the object
	Expense getExpneseById(Long id);
	
	
}
