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

import com.vili.sorsfinance.api.entities.Phone;
import com.vili.sorsfinance.api.entities.dto.PhoneDTO;
import com.vili.sorsfinance.api.entities.filters.PhoneFilter;
import com.vili.sorsfinance.api.framework.DefaultResource;
import com.vili.sorsfinance.api.framework.EntityFilter;
import com.vili.sorsfinance.api.framework.Request;
import com.vili.sorsfinance.api.repositories.PhoneRepository;

@RestController
@RequestMapping(value = "/phones")
public class PhoneResource extends DefaultResource<Phone, PhoneDTO>{

	@Autowired
	PhoneRepository repo;

	@Override
	@SuppressWarnings("unchecked")
	protected <U extends EntityFilter<Phone>> U getFilter() {
		return (U) new PhoneFilter(Request.from(getRequest()), repo);
	}
	
	@Override
	@PostMapping
	public ResponseEntity<Phone> insert(@Valid @RequestBody PhoneDTO dto) {
		Phone obj = Phone.fromDTO(dto);
		obj = service.save(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(obj);
	}
}
