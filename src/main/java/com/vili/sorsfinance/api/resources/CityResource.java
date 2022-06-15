package com.vili.sorsfinance.api.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vili.sorsfinance.api.entities.City;
import com.vili.sorsfinance.api.entities.filters.CityFilter;
import com.vili.sorsfinance.api.framework.DTO;
import com.vili.sorsfinance.api.framework.DefaultResource;
import com.vili.sorsfinance.api.framework.EntityFilter;
import com.vili.sorsfinance.api.framework.Request;
import com.vili.sorsfinance.api.repositories.CityRepository;

@RestController
@RequestMapping(value = "/cities")
public class CityResource extends DefaultResource<City, DTO<City>> {

	@Autowired
	CityRepository repo;

	@Override
	@SuppressWarnings("unchecked")
	protected <U extends EntityFilter<City>> U getFilter() {
		return (U) new CityFilter(Request.from(getRequest()), repo);
	}
}
