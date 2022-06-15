package com.vili.sorsfinance.api.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vili.sorsfinance.api.entities.Contact;
import com.vili.sorsfinance.api.entities.filters.ContactFilter;
import com.vili.sorsfinance.api.framework.DTO;
import com.vili.sorsfinance.api.framework.DefaultResource;
import com.vili.sorsfinance.api.framework.EntityFilter;
import com.vili.sorsfinance.api.framework.Request;
import com.vili.sorsfinance.api.repositories.ContactRepository;

@RestController
@RequestMapping(value = "/contacts")
public class ContactResource extends DefaultResource<Contact, DTO<Contact>> {

	@Autowired
	ContactRepository repo;

	@Override
	@SuppressWarnings("unchecked")
	protected <U extends EntityFilter<Contact>> U getFilter() {
		return (U) new ContactFilter(Request.from(getRequest()), repo);
	}
}
