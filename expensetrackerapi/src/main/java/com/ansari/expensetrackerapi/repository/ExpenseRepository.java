package com.ansari.expensetrackerapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ansari.expensetrackerapi.entity.Expense;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {

}
