package com.vili.sorsfinance.api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vili.sorsfinance.api.entities.Country;

public interface CountryRepository extends JpaRepository<Country, Long> {

	List<Country> findByNameIgnoreCase(String name);
	List<Country> findByAcronymIgnoreCase(String acronym);
}
