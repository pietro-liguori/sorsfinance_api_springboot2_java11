package com.vili.sorsfinance.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vili.sorsfinance.entities.Country;

public interface CountryRepository extends JpaRepository<Country, Long> {

}
