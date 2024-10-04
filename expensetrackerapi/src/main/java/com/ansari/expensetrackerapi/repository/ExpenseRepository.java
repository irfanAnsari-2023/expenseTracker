package com.ansari.expensetrackerapi.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ansari.expensetrackerapi.entity.Expense;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {

	Page<Expense> findByCategory(String category, Pageable pageable);
	
	Page<Expense> findByNameContaining(String keyword, Pageable pageable);
	
	List<Expense> findByDateBetween(Date startDate, Date endDate);
}
