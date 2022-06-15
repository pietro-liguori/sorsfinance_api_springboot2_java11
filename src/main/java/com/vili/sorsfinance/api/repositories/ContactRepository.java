package com.vili.sorsfinance.api.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.vili.sorsfinance.api.entities.Contact;

public interface ContactRepository extends JpaRepository<Contact, Long> {

	Page<Contact> findByPreferredContact(Integer preferredContact, Pageable pageable);
	Page<Contact> findByOwnerId(Long owner, Pageable pageable);
	Page<Contact> findByOwnerIdAndPreferredContact(Long owner, Integer preferredContact, Pageable pageable);
}
