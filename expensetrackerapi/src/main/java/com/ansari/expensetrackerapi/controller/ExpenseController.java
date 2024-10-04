package com.ansari.expensetrackerapi.controller;

import java.sql.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.ansari.expensetrackerapi.entity.Expense;
import com.ansari.expensetrackerapi.service.ExpenseService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@RestController
public class ExpenseController {

	@Autowired
	private ExpenseService expenseService;
	
	@Operation(summary = " Get All Expenses")
	@GetMapping("/expenses")
	public ResponseEntity<List<Expense>> getAllExpenses(Pageable pageable){
		List<Expense> expenses = expenseService.getAllExpense(pageable).toList();
		if(expenses.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(expenses,HttpStatus.OK );
	}
	
	@Operation(summary = " Get Expense Details By Expense ID")
	@GetMapping("/expenses/{id}")
	public ResponseEntity<Expense> getExpenseById(@PathVariable Long id) {
		 Expense expense = expenseService.getExpneseById(id);
		    return new ResponseEntity<>(expense, HttpStatus.OK);
	}
	
	@Operation(summary = " Delete Expense By ID")
	@DeleteMapping("/expenses")
	public  ResponseEntity<Void> deleteExpenseById(@RequestParam Long id) {
		expenseService.deleteExpenseById(id);
	    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@Operation(summary = " Save Expense Details")
	@PostMapping("/expenses")
	public ResponseEntity<Expense> saveExpenseDetails(@Valid @RequestBody Expense expense) {
		Expense savedExpense = expenseService.saveExpenseDetails(expense);
	    return new ResponseEntity<>(savedExpense, HttpStatus.CREATED);
	}
	
	@Operation(summary = "Update Expense Details By ID")
	@PutMapping("/expenses/{id}")
	public ResponseEntity<Expense> updateExpenseDetails(@RequestBody Expense expense, @PathVariable Long id) {
		Expense updatedExpense = expenseService.updateExpenseDetails(id, expense);
	    return new ResponseEntity<>(updatedExpense, HttpStatus.OK);
	}
	
	@Operation(summary = " Get All Expenses By Category ")
	@GetMapping("/expenses/category")
	public ResponseEntity<List<Expense>> getExpenseByCategory(@RequestParam String category, Pageable pageable){
		List<Expense> expenses = expenseService.readByCategory(category, pageable);
	    return new ResponseEntity<>(expenses, HttpStatus.OK);
	}
	
	@Operation(summary = " Get All Expenses By Expense's Name")
	@GetMapping("/expenses/name")
	public  ResponseEntity<List<Expense>> getAllExpenseByName(@RequestParam String name, Pageable pageable){
		List<Expense> expenses = expenseService.readByName(name, pageable);
	    return new ResponseEntity<>(expenses, HttpStatus.OK);
	}
	
	@Operation(summary = " Get Expenses By Date ")
	@GetMapping("/expenses/date")
	public ResponseEntity<List<Expense>> getAllExpensesByDate(@RequestParam (required = false) Date startDate, @RequestParam(required = false) Date endDate){
		List<Expense> expenses = expenseService.readByDate(startDate, endDate);
	    return new ResponseEntity<>(expenses, HttpStatus.OK);
	}
	
	@Operation(summary = " Delete All Expenses")
	@DeleteMapping("/expensesAll")
    public ResponseEntity<Void> deleteAllExpenses() {
        expenseService.deleteAllExpenses();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
 