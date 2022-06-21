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

import com.vili.sorsfinance.api.entities.City;
import com.vili.sorsfinance.api.entities.dto.CityDTO;
import com.vili.sorsfinance.api.entities.filters.CityFilter;
import com.vili.sorsfinance.api.framework.DefaultResource;
import com.vili.sorsfinance.api.framework.EntityFilter;
import com.vili.sorsfinance.api.framework.Request;
import com.vili.sorsfinance.api.repositories.CityRepository;

@RestController
@RequestMapping(value = "/cities")
public class CityResource extends DefaultResource<City, CityDTO> {

	@Autowired
	CityRepository repo;

	@Override
	@SuppressWarnings("unchecked")
	protected <U extends EntityFilter<City>> U getFilter() {
		return (U) new CityFilter(Request.from(getRequest()), repo);
	}
	
	@Override
	@PostMapping
	public ResponseEntity<City> insert(@Valid @RequestBody CityDTO dto) {
		City obj = City.fromDTO(dto);
		obj = service.save(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(obj);
	}
}
