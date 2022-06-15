package com.vili.sorsfinance.api.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.vili.sorsfinance.api.entities.City;

public interface CityRepository extends JpaRepository<City, Long> {

	Page<City> findByStateId(Long state, Pageable pageable);
	Page<City> findByStateCountryId(Long country, Pageable pageable);
}