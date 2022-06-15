package com.vili.sorsfinance.api.framework;

import java.net.URI;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

public class DefaultResource<T extends BusEntity, S extends DTO<T>> {

	@Autowired 
	HttpServletRequest request;
	@Autowired 
	DefaultService<T> service;

	@GetMapping
	public ResponseEntity<List<T>> findAll() {
		List<T> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}

	@GetMapping(value = "/filter")
	public ResponseEntity<Page<T>> findPage() {
		Page<T> obj = service.findPage(getFilter());
		return ResponseEntity.ok().body(obj);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<T> findById(@PathVariable Long id) {
		T obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}

	@PostMapping
	public ResponseEntity<T> insert(@RequestBody S dto) {
		T obj = (T) T.fromDTO(dto);
		obj = service.save(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(obj);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

	@PutMapping
	public ResponseEntity<T> update(@RequestBody S dto) {
		T obj = (T) T.fromDTO(dto);
		obj = service.save(obj);
		return ResponseEntity.ok().body(obj);
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	@SuppressWarnings("unchecked")
	protected <U extends EntityFilter<T>> U getFilter() {
		return (U) EntityFilter.paging(Request.from(request));
	}
}
