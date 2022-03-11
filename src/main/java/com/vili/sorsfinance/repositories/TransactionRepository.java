package com.vili.sorsfinance.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vili.sorsfinance.entities.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

}
