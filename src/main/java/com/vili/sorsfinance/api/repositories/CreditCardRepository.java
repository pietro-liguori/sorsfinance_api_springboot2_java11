package com.vili.sorsfinance.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vili.sorsfinance.api.entities.CreditCard;

public interface CreditCardRepository extends JpaRepository<CreditCard, Long> {

}
