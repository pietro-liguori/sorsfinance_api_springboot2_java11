package com.vili.sorsfinance.api.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.vili.sorsfinance.api.entities.State;

public interface StateRepository extends JpaRepository<State, Long> {

	Page<State> findByCountryId(Long country, Pageable pageable);
}
