package com.vili.sorsfinance.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vili.sorsfinance.api.entities.Wallet;

public interface WalletRepository extends JpaRepository<Wallet, Long> {

}
