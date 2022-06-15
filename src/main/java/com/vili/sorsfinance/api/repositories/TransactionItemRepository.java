package com.vili.sorsfinance.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vili.sorsfinance.api.entities.TransactionItem;

public interface TransactionItemRepository extends JpaRepository<TransactionItem, Long> {

}
