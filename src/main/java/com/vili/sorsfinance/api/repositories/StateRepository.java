package com.vili.sorsfinance.api.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.vili.sorsfinance.api.entities.State;

public interface StateRepository extends JpaRepository<State, Long> {

	List<State> findByNameIgnoreCase(String name);
	List<State> findByAcronymIgnoreCase(String acronym);
	Page<State> findByCountryId(Long country, Pageable pageable);
}
