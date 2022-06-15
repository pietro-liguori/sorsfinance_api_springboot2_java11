package com.vili.sorsfinance.api.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.vili.sorsfinance.api.entities.Email;

public interface EmailRepository extends JpaRepository<Email, Long> {

	Page<Email> findByContactOwnerId(Long owner, Pageable pageable);
	Page<Email> findByPreferred(Boolean preferred, Pageable pageable);
	Page<Email> findByContactOwnerIdAndPreferred(Long owner, Boolean preferred, Pageable pageable);
}
