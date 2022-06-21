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

import com.vili.sorsfinance.api.entities.State;
import com.vili.sorsfinance.api.entities.dto.StateDTO;
import com.vili.sorsfinance.api.entities.filters.StateFilter;
import com.vili.sorsfinance.api.framework.DefaultResource;
import com.vili.sorsfinance.api.framework.EntityFilter;
import com.vili.sorsfinance.api.framework.Request;
import com.vili.sorsfinance.api.repositories.StateRepository;

@RestController
@RequestMapping(value = "/states")
public class StateResource extends DefaultResource<State, StateDTO> {

	@Autowired
	StateRepository repo;

	@Override
	@SuppressWarnings("unchecked")
	protected <U extends EntityFilter<State>> U getFilter() {
		return (U) new StateFilter(Request.from(getRequest()), repo);
	}
	
	@Override
	@PostMapping
	public ResponseEntity<State> insert(@Valid @RequestBody StateDTO dto) {
		State obj = State.fromDTO(dto);
		obj = service.save(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(obj);
	}
}
