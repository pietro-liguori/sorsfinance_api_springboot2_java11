package com.vili.sorsfinance.api.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vili.sorsfinance.api.entities.Phone;
import com.vili.sorsfinance.api.entities.filters.PhoneFilter;
import com.vili.sorsfinance.api.framework.DTO;
import com.vili.sorsfinance.api.framework.DefaultResource;
import com.vili.sorsfinance.api.framework.EntityFilter;
import com.vili.sorsfinance.api.framework.Request;
import com.vili.sorsfinance.api.repositories.PhoneRepository;

@RestController
@RequestMapping(value = "/phones")
public class PhoneResource extends DefaultResource<Phone, DTO<Phone>>{

	@Autowired
	PhoneRepository repo;

	@Override
	@SuppressWarnings("unchecked")
	protected <U extends EntityFilter<Phone>> U getFilter() {
		return (U) new PhoneFilter(Request.from(getRequest()), repo);
	}
}
