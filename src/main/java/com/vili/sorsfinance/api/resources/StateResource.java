package com.vili.sorsfinance.api.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vili.sorsfinance.api.entities.State;
import com.vili.sorsfinance.api.entities.filters.StateFilter;
import com.vili.sorsfinance.api.framework.DTO;
import com.vili.sorsfinance.api.framework.DefaultResource;
import com.vili.sorsfinance.api.framework.EntityFilter;
import com.vili.sorsfinance.api.framework.Request;
import com.vili.sorsfinance.api.repositories.StateRepository;

@RestController
@RequestMapping(value = "/states")
public class StateResource extends DefaultResource<State, DTO<State>> {

	@Autowired
	StateRepository repo;

	@Override
	@SuppressWarnings("unchecked")
	protected <U extends EntityFilter<State>> U getFilter() {
		return (U) new StateFilter(Request.from(getRequest()), repo);
	}
}
