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

import com.vili.sorsfinance.entities.Person;
import com.vili.sorsfinance.entities.enums.PersonProfile;
import com.vili.sorsfinance.services.PersonService;

@RestController
@RequestMapping(value = "/people")
public class PersonResource {

	@Autowired
	private PersonService service;

	@GetMapping
	public ResponseEntity<List<Person>> findAll() {
		List<Person> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}

	@GetMapping(value = "/type:{type}")
	public ResponseEntity<List<Person>> findByProfile(@PathVariable Integer type) {
		List<Person> list = service.findAll().stream()
				.filter(person -> person.getProfile().equals(PersonProfile.toEnum(type)))
				.toList();
		return ResponseEntity.ok().body(list);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Person> findById(@PathVariable Long id) {
		Person obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}

	@PostMapping
	public ResponseEntity<Person> insert(@RequestBody Person obj) {
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
//	public ResponseEntity<Person> update(@PathVariable Long id, @RequestBody Person obj) {
//		obj = service.update(id, obj);
//		return ResponseEntity.ok().body(obj);
//	}
}
