package com.vili.sorsfinance.api.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.vili.sorsfinance.api.entities.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {

	Page<Person> findByBranchId(Long branch, Pageable pageable);
	Page<Person> findByType(Integer type, Pageable pageable);
	Page<Person> findByProfile(Integer profile, Pageable pageable);
	Page<Person> findByBranchIdAndType(Long branch, Integer type, Pageable pageable);
	Page<Person> findByBranchIdAndProfile(Long branch, Integer profile, Pageable pageable);
	Page<Person> findByTypeAndProfile(Integer type, Integer profile, Pageable pageable);
	Page<Person> findByBranchIdAndTypeAndProfile(Long branch, Integer type, Integer profile, Pageable pageable);
}
