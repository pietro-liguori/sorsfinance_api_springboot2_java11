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

import com.vili.sorsfinance.entities.Transaction;
import com.vili.sorsfinance.services.TransactionService;

@RestController
@RequestMapping(value = "/transactions")
public class TransactionResource {

	@Autowired
	private TransactionService service;

	@GetMapping
	public ResponseEntity<List<Transaction>> findAll() {
		List<Transaction> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}

	@GetMapping(value = "/category:{category}")
	public ResponseEntity<List<Transaction>> findByCategory(@PathVariable String category) {
		List<Transaction> list = service.findAll().stream()
				.filter(tr -> tr.getCategory().getName().equals(category.toUpperCase()))
				.toList();
		return ResponseEntity.ok().body(list);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Transaction> findById(@PathVariable Long id) {
		Transaction obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}

	@PostMapping
	public ResponseEntity<Transaction> insert(@RequestBody Transaction obj) {
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
//	public ResponseEntity<Transaction> update(@PathVariable Long id, @RequestBody Transaction obj) {
//		obj = service.update(id, obj);
//		return ResponseEntity.ok().body(obj);
//	}
}
