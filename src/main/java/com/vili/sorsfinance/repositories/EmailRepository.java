package com.vili.sorsfinance.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vili.sorsfinance.entities.Email;

public interface EmailRepository extends JpaRepository<Email, Long> {

}