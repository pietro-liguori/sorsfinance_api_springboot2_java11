package com.vili.sorsfinance.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vili.sorsfinance.entities.CreditCardStatement;

public interface CreditCardStatementRepository extends JpaRepository<CreditCardStatement, Long> {

}
