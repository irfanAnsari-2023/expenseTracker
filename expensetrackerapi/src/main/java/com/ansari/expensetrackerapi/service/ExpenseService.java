package com.ansari.expensetrackerapi.service;

import java.util.List;

import com.ansari.expensetrackerapi.entity.Expense;

public interface ExpenseService {
	
	List<Expense> getAllExpense();
	
//	Define a mtd which will take the expense id as a param and going to return the object
	Expense getExpneseById(Long id);
//	
	void deleteExpenseById(Long id);
	
//	this will take expense object as param and will return expense object
	Expense saveExpenseDetails(Expense expense);
	
//	this will take expense obj as a param and will return back an obj
	Expense updateExpenseDetails(Long id, Expense expense);
	
	
}
