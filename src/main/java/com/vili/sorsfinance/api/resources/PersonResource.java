package com.vili.sorsfinance.api.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vili.sorsfinance.api.entities.Person;
import com.vili.sorsfinance.api.entities.filters.PersonFilter;
import com.vili.sorsfinance.api.framework.DTO;
import com.vili.sorsfinance.api.framework.DefaultResource;
import com.vili.sorsfinance.api.framework.EntityFilter;
import com.vili.sorsfinance.api.framework.Request;
import com.vili.sorsfinance.api.repositories.PersonRepository;

@RestController
@RequestMapping(value = "/people")
public class PersonResource extends DefaultResource<Person, DTO<Person>> {

	@Autowired 
	PersonRepository repo;

	@Override
	@SuppressWarnings("unchecked")
	protected <U extends EntityFilter<Person>> U getFilter() {
		return (U) new PersonFilter(Request.from(getRequest()), repo);
	}
}
