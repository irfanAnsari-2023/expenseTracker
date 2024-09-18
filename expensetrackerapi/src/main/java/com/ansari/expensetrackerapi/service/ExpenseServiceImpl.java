package com.ansari.expensetrackerapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.ansari.expensetrackerapi.entity.Expense;
import com.ansari.expensetrackerapi.repository.ExpenseRepository;

@Service
public class ExpenseServiceImpl implements ExpenseService{
	

	@Autowired
	private ExpenseRepository expenseRepo;
	
	@Override
	public List<Expense> getAllExpense() {
		return expenseRepo.findAll();
	}
	@Override
	public Expense getExpneseById(Long id) {
		Optional<Expense> expense = expenseRepo.findById(id);
		if(expense.isPresent()) {
			return expense.get();		
		}else {
			throw new RuntimeException("Expense is not found for the id : "+id);
		}
		
		
	}

	
}
