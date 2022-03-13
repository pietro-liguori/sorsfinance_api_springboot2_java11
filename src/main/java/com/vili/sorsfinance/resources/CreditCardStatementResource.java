package com.vili.sorsfinance.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.vili.sorsfinance.entities.CreditCardStatement;
import com.vili.sorsfinance.services.CreditCardStatementService;

@RestController
@RequestMapping(value = "/accounts/creditcardstatements")
public class CreditCardStatementResource {

	@Autowired
	private CreditCardStatementService service;

	@GetMapping
	public ResponseEntity<List<CreditCardStatement>> findAll() {
		List<CreditCardStatement> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<CreditCardStatement> findById(@PathVariable Long id) {
		CreditCardStatement obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}

	@PostMapping
	public ResponseEntity<CreditCardStatement> insert(@RequestBody CreditCardStatement obj) {
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(obj);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

//	@PutMapping(value = "/{id}")
//	public ResponseEntity<CreditCardStatement> update(@PathVariable Long id, @RequestBody CreditCardStatement obj) {
//		obj = service.update(id, obj);
//		return ResponseEntity.ok().body(obj);
//	}
}
