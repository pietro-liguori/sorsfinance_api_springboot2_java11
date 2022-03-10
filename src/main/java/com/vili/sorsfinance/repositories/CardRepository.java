package com.vili.sorsfinance.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vili.sorsfinance.entities.Card;

public interface CardRepository extends JpaRepository<Card, Long> {

}
