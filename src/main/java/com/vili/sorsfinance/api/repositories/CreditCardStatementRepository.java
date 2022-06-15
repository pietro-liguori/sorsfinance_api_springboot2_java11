package com.vili.sorsfinance.api.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.vili.sorsfinance.api.entities.CreditCardStatement;

public interface CreditCardStatementRepository extends JpaRepository<CreditCardStatement, Long> {

	Page<CreditCardStatement> findByStatus(Integer status, Pageable pageable);
}
