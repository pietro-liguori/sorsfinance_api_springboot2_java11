package com.vili.sorsfinance.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vili.sorsfinance.entities.Contact;

public interface ContactRepository extends JpaRepository<Contact, Long> {

}
