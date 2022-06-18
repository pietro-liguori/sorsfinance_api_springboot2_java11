package com.vili.sorsfinance.api.resources;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.vili.sorsfinance.api.entities.Contact;
import com.vili.sorsfinance.api.entities.dto.ContactDTO;
import com.vili.sorsfinance.api.entities.filters.ContactFilter;
import com.vili.sorsfinance.api.framework.DefaultResource;
import com.vili.sorsfinance.api.framework.EntityFilter;
import com.vili.sorsfinance.api.framework.Request;
import com.vili.sorsfinance.api.repositories.ContactRepository;

@RestController
@RequestMapping(value = "/contacts")
public class ContactResource extends DefaultResource<Contact, ContactDTO> {

	@Autowired
	ContactRepository repo;

	@Override
	@SuppressWarnings("unchecked")
	protected <U extends EntityFilter<Contact>> U getFilter() {
		return (U) new ContactFilter(Request.from(getRequest()), repo);
	}
	
	@Override
	@PostMapping
	public ResponseEntity<Contact> insert(@Valid @RequestBody ContactDTO dto) {
		Contact obj = Contact.fromDTO(dto);
		obj = service.save(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(obj);
	}
}
