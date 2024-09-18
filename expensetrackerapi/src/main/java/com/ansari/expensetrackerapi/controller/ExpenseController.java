package com.ansari.expensetrackerapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ansari.expensetrackerapi.entity.Expense;
import com.ansari.expensetrackerapi.service.ExpenseService;

@RestController
public class ExpenseController {

	@Autowired
	private ExpenseService expenseService;
	
	@GetMapping("/expenses")
	public List<Expense> getAllExpenses(){
		return expenseService.getAllExpense();
	}
	
	@GetMapping("/expenses/{id}")
	public Expense getExpenseById(@PathVariable Long id) {
		return expenseService.getExpneseById(id);
	}
	
	@DeleteMapping("/expenses")
	public String deleteExpenseById(@RequestParam Long id) {
		return "Deleted the expense object by its id: "+id;
	}
}
 