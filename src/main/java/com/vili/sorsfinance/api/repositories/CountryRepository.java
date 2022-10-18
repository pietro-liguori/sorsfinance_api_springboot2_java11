package com.vili.sorsfinance.api.repositories;

import java.util.List;

import com.vili.sorsfinance.api.domain.Country;
import com.vili.sorsfinance.framework.annotations.EntityRef;
import com.vili.sorsfinance.framework.repositories.IRepository;

@EntityRef(Country.class)
public interface CountryRepository extends IRepository<Country> {

	List<Country> findByAcronymIgnoreCase(String acronym);
}
