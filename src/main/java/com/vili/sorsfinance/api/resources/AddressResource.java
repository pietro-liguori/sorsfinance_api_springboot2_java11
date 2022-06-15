package com.vili.sorsfinance.api.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vili.sorsfinance.api.entities.Address;
import com.vili.sorsfinance.api.entities.filters.AddressFilter;
import com.vili.sorsfinance.api.framework.DTO;
import com.vili.sorsfinance.api.framework.DefaultResource;
import com.vili.sorsfinance.api.framework.EntityFilter;
import com.vili.sorsfinance.api.framework.Request;
import com.vili.sorsfinance.api.repositories.AddressRepository;

@RestController
@RequestMapping(value = "/addresses")
public class AddressResource extends DefaultResource<Address, DTO<Address>> {

	@Autowired AddressRepository repo;

	@Override
	@SuppressWarnings("unchecked")
	protected <U extends EntityFilter<Address>> U getFilter() {
		Request req = Request.from(getRequest());
		return (U) new AddressFilter(req, repo);
	}
}
