package com.vili.sorsfinance.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vili.sorsfinance.entities.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {

}
