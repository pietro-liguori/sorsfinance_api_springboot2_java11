package com.vili.sorsfinance.api.repositories;

import java.util.List;

import com.vili.sorsfinance.api.domain.Country;
import com.vili.sorsfinance.framework.annotations.EntityRef;
import com.vili.sorsfinance.framework.interfaces.IRepository;

@EntityRef(Country.class)
public interface CountryRepository extends IRepository<Country> {

	List<Country> findByNameIgnoreCase(String name);
	List<Country> findByAcronymIgnoreCase(String acronym);
}
