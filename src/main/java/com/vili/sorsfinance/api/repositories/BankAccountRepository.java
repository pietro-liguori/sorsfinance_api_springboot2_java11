package com.vili.sorsfinance.api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vili.sorsfinance.api.entities.BankAccount;

public interface BankAccountRepository extends JpaRepository<BankAccount, Long> {

	List<BankAccount> findByCardsId(Long card);
}
