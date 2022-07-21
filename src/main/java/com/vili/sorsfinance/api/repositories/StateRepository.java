package com.vili.sorsfinance.api.repositories;

import java.util.List;

import com.vili.sorsfinance.api.domain.State;
import com.vili.sorsfinance.framework.annotations.EntityRef;
import com.vili.sorsfinance.framework.interfaces.IRepository;

@EntityRef(State.class)
public interface StateRepository extends IRepository<State> {

	List<State> findByNameIgnoreCase(String name);
	List<State> findByAcronymIgnoreCase(String acronym);
}
