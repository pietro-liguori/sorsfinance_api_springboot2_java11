package com.vili.sorsfinance.api.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vili.sorsfinance.api.entities.Email;
import com.vili.sorsfinance.api.entities.filters.EmailFilter;
import com.vili.sorsfinance.api.framework.DTO;
import com.vili.sorsfinance.api.framework.DefaultResource;
import com.vili.sorsfinance.api.framework.EntityFilter;
import com.vili.sorsfinance.api.framework.Request;
import com.vili.sorsfinance.api.repositories.EmailRepository;

@RestController
@RequestMapping(value = "/emails")
public class EmailResource extends DefaultResource<Email, DTO<Email>> {

	@Autowired
	EmailRepository repo;
	
	@Override
	@SuppressWarnings("unchecked")
	protected <U extends EntityFilter<Email>> U getFilter() {
		return (U) new EmailFilter(Request.from(getRequest()), repo);
	}
}
