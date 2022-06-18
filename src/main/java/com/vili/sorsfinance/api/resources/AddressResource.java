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

import com.vili.sorsfinance.api.entities.Address;
import com.vili.sorsfinance.api.entities.dto.AddressDTO;
import com.vili.sorsfinance.api.entities.filters.AddressFilter;
import com.vili.sorsfinance.api.framework.DefaultResource;
import com.vili.sorsfinance.api.framework.EntityFilter;
import com.vili.sorsfinance.api.framework.Request;
import com.vili.sorsfinance.api.repositories.AddressRepository;

@RestController
@RequestMapping(value = "/addresses")
public class AddressResource extends DefaultResource<Address, AddressDTO> {

	@Autowired AddressRepository repo;

	@Override
	@SuppressWarnings("unchecked")
	protected <U extends EntityFilter<Address>> U getFilter() {
		Request req = Request.from(getRequest());
		return (U) new AddressFilter(req, repo);
	}
	
	@Override
	@PostMapping
	public ResponseEntity<Address> insert(@Valid @RequestBody AddressDTO dto) {
		Address obj = Address.fromDTO(dto);
		obj = service.save(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(obj);
	}
}
