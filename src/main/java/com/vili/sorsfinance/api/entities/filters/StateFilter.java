package com.vili.sorsfinance.api.entities.filters;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.vili.sorsfinance.api.entities.State;
import com.vili.sorsfinance.api.framework.EntityFilter;
import com.vili.sorsfinance.api.framework.Request;
import com.vili.sorsfinance.api.repositories.StateRepository;

public class StateFilter extends EntityFilter<State> {

	public StateFilter(Request request, StateRepository repository) {
		super(request, repository);
	}

	@Override
	public Page<State> apply() {
		Map<String, ?> filter = getFilter();
		Pageable pageable = getPageRequest();
		StateRepository repository = (StateRepository) getRepository();
		
		if (filter != null) {
			boolean hasCountry = filter.get("country") != null;

			if (hasCountry) {
				Long country = (Long) filter.get("country");
				return repository.findByCountryId(country, pageable);
			}
		}

		return repository.findAll(pageable);
	}
}
