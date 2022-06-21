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

import com.vili.sorsfinance.api.entities.Person;
import com.vili.sorsfinance.api.entities.dto.PersonDTO;
import com.vili.sorsfinance.api.entities.filters.PersonFilter;
import com.vili.sorsfinance.api.framework.DefaultResource;
import com.vili.sorsfinance.api.framework.EntityFilter;
import com.vili.sorsfinance.api.framework.Request;
import com.vili.sorsfinance.api.repositories.PersonRepository;

@RestController
@RequestMapping(value = "/people")
public class PersonResource extends DefaultResource<Person, PersonDTO> {

	@Autowired 
	PersonRepository repo;

	@Override
	@SuppressWarnings("unchecked")
	protected <U extends EntityFilter<Person>> U getFilter() {
		return (U) new PersonFilter(Request.from(getRequest()), repo);
	}
	
	@Override
	@PostMapping
	public ResponseEntity<Person> insert(@Valid @RequestBody PersonDTO dto) {
		Person obj = Person.fromDTO(dto);
		obj = service.save(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(obj);
	}
}
