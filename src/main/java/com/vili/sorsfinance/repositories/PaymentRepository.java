package com.vili.sorsfinance.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vili.sorsfinance.entities.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

}
