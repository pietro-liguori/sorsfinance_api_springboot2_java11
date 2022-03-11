package com.vili.sorsfinance.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vili.sorsfinance.entities.TransactionItem;

public interface TransactionItemRepository extends JpaRepository<TransactionItem, Long> {

}
