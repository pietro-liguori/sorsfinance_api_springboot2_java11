package com.vili.sorsfinance.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vili.sorsfinance.entities.PaymentInstallment;

public interface PaymentInstallmentRepository extends JpaRepository<PaymentInstallment, Long> {

}
