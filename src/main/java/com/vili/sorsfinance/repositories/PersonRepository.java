package com.vili.sorsfinance.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vili.sorsfinance.entities.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {

}
