package com.vili.sorsfinance.api.entities.filters;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.vili.sorsfinance.api.entities.City;
import com.vili.sorsfinance.api.framework.EntityFilter;
import com.vili.sorsfinance.api.framework.Request;
import com.vili.sorsfinance.api.repositories.CityRepository;

public class CityFilter extends EntityFilter<City> {

	public CityFilter(Request request, CityRepository repository) {
		super(request, repository);
	}

	@Override
	public Page<City> apply() {
		Map<String, ?> filter = getFilter();
		Pageable pageable = getPageRequest();
		CityRepository repository = (CityRepository) getRepository();
		
		if (filter != null) {
			boolean hasState = filter.get("state") != null;
			boolean hasCountry = filter.get("country") != null;

			if (hasState) {
				Long state = (Long) filter.get("state");
				return repository.findByStateId(state, pageable);
			}

			if (!hasState && hasCountry) {
				Long country = (Long) filter.get("country");
				return repository.findByStateCountryId(country, pageable);
			}
		}

		return repository.findAll(pageable);
	}
}
