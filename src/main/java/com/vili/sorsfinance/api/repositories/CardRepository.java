package com.vili.sorsfinance.api.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.vili.sorsfinance.api.entities.Card;

public interface CardRepository extends JpaRepository<Card, Long> {

	Page<Card> findByType(Integer type, Pageable pageable);
	Page<Card> findByStatus(Integer status, Pageable pageable);
	Page<Card> findByTypeAndStatus(Integer status, Integer type, Pageable pageable);
}
