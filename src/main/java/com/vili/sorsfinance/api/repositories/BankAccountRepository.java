package com.vili.sorsfinance.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vili.sorsfinance.api.entities.BankAccount;

public interface BankAccountRepository extends JpaRepository<BankAccount, Long> {

}
