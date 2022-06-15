package com.vili.sorsfinance.api.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.vili.sorsfinance.api.entities.CreditInstallment;

public interface CreditInstallmentRepository extends JpaRepository<CreditInstallment, Long> {

	Page<CreditInstallment> findByStatus(Integer status, Pageable pageable);
}
