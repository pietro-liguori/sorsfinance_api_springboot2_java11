package com.vili.sorsfinance.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vili.sorsfinance.api.entities.CreditPayment;

public interface CreditPaymentRepository extends JpaRepository<CreditPayment, Long> {

}
