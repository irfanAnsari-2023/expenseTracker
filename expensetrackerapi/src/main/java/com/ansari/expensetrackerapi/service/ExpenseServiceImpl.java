package com.ansari.expensetrackerapi.service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.ansari.expensetrackerapi.entity.Expense;
import com.ansari.expensetrackerapi.exceptions.ExpenseNotFoundException;
import com.ansari.expensetrackerapi.repository.ExpenseRepository;

@Service
public  class ExpenseServiceImpl implements ExpenseService{
	

	@Autowired
	private ExpenseRepository expenseRepo;
	
	@Override
	public Page<Expense> getAllExpense(Pageable pageable) {
		return expenseRepo.findAll(pageable);
	}
	
	@Override
	public Expense getExpneseById(Long id) {
		Optional<Expense> expense = expenseRepo.findById(id);
		if(expense.isPresent()) {
			return expense.get();		
		}
		throw new ExpenseNotFoundException("Expense is not found for the id "+id);
	}
	
	@Override
	public void deleteExpenseById(Long id) {
		Expense expense = getExpneseById(id);
		expenseRepo.delete(expense);
		
	}
	
	@Override
	public Expense saveExpenseDetails(Expense expense) {
		return expenseRepo.save(expense);
	}
	
	@Override
	public Expense updateExpenseDetails(Long id, Expense expense) {
		Expense existingExpense = getExpneseById(id);
		existingExpense.setName(expense.getName()!= null ? expense.getName() : existingExpense.getName());
		existingExpense.setDescription(expense.getDescription()!= null ? expense.getDescription() : existingExpense.getDescription());
		existingExpense.setCategory(expense.getCategory()!= null ? expense.getCategory() : existingExpense.getCategory());
		existingExpense.setAmount(expense.getAmount()!= null ? expense.getAmount() : existingExpense.getAmount());
		existingExpense.setDate(expense.getDate()!= null ? expense.getDate() : existingExpense.getDate());
		return expenseRepo.save(existingExpense);
	}
	
	@Override
	public List<Expense> readByCategory(String category, Pageable pageable) {
		List<Expense> expenses = expenseRepo.findByCategory(category, pageable).toList();
		if(expenses.isEmpty()) {
			throw new ExpenseNotFoundException("No expense found for the category: "+ category);
		}
		return expenses;
	}
	
	@Override
	public List<Expense> readByName(String name, Pageable pageable) {
		List<Expense> expenses = expenseRepo.findByNameContaining(name, pageable).toList();
		if(expenses.isEmpty()) {
			throw new ExpenseNotFoundException("No expense found with the name: "+ name);
		}
		return expenses;
	}
	
	@Override
	public List<Expense> readByDate(Date startDate, Date endDate) {
		if(startDate == null) {
			startDate = new Date(0);
		}
		if(endDate == null) {
			endDate = new Date(System.currentTimeMillis());
		}
		List<Expense> expenses = expenseRepo.findByDateBetween(startDate, endDate);
	    if (expenses.isEmpty()) {
	        throw new ExpenseNotFoundException("No expenses found between the dates " + startDate + " and " + endDate);
	    }
	    return expenses;
	}
	
	@Override
	public void deleteAllExpenses() {
		expenseRepo.deleteAll();
		
	}
	

	
}
