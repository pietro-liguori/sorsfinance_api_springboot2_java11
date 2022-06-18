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

import com.vili.sorsfinance.api.entities.Email;
import com.vili.sorsfinance.api.entities.dto.EmailDTO;
import com.vili.sorsfinance.api.entities.filters.EmailFilter;
import com.vili.sorsfinance.api.framework.DefaultResource;
import com.vili.sorsfinance.api.framework.EntityFilter;
import com.vili.sorsfinance.api.framework.Request;
import com.vili.sorsfinance.api.repositories.EmailRepository;

@RestController
@RequestMapping(value = "/emails")
public class EmailResource extends DefaultResource<Email, EmailDTO> {

	@Autowired
	EmailRepository repo;
	
	@Override
	@SuppressWarnings("unchecked")
	protected <U extends EntityFilter<Email>> U getFilter() {
		return (U) new EmailFilter(Request.from(getRequest()), repo);
	}
	
	@Override
	@PostMapping
	public ResponseEntity<Email> insert(@Valid @RequestBody EmailDTO dto) {
		Email obj = Email.fromDTO(dto);
		obj = service.save(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(obj);
	}
}
