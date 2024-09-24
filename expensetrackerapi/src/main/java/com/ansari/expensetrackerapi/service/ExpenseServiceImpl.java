package com.ansari.expensetrackerapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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
//		else {
//			throw new RuntimeException("Expense is not found for the id : "+id);
//			writing custom exceptions
//		}
			
	}
	
	@Override
	public void deleteExpenseById(Long id) {
		expenseRepo.deleteById(id);
		
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
	

	
}
