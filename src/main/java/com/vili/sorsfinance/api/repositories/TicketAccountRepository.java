package com.vili.sorsfinance.api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vili.sorsfinance.api.entities.TicketAccount;

public interface TicketAccountRepository extends JpaRepository<TicketAccount, Long> {

	List<TicketAccount> findByCardsId(Long card);
}
