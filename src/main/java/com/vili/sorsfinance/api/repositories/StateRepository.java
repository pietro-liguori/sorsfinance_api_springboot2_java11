package com.vili.sorsfinance.api.repositories;

import java.util.List;

import com.vili.sorsfinance.api.domain.State;
import com.vili.sorsfinance.framework.annotations.EntityRef;
import com.vili.sorsfinance.framework.repositories.IRepository;

@EntityRef(State.class)
public interface StateRepository extends IRepository<State> {

	List<State> findByAcronymIgnoreCaseAndCountryId(String acronym, Long countryId);
	List<State> findByNameIgnoreCaseAndCountryId(String name, Long countryId);
}
