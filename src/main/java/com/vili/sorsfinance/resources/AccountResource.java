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

import com.vili.sorsfinance.entities.Account;
import com.vili.sorsfinance.entities.Card;
import com.vili.sorsfinance.entities.CreditCardStatement;
import com.vili.sorsfinance.services.AccountService;
import com.vili.sorsfinance.services.CardService;
import com.vili.sorsfinance.services.CreditCardStatementService;

@RestController
@RequestMapping(value = "/accounts")
public class AccountResource {

	@Autowired
	private AccountService service;
	@Autowired
	private CreditCardStatementService creditCardStatementService;
	@Autowired
	private CardService cardService;

	@GetMapping
	public ResponseEntity<List<Account>> findAll() {
		List<Account> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Account> findById(@PathVariable Long id) {
		Account obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}

	@GetMapping(value = "/{id}/cards")
	public ResponseEntity<List<Card>> findCards(@PathVariable Long id) {
		service.findById(id);
		List<Card> list = cardService.findAll().stream().filter(obj -> obj.getAccount().getId().equals(id)).toList();
		if (list.size() > 0) return ResponseEntity.ok().body(list);
		return ResponseEntity.notFound().build();
	}

	@GetMapping(value = "/{id}/creditcardstatements")
	public ResponseEntity<List<CreditCardStatement>> findCreditCardStatements(@PathVariable Long id) {
		service.findById(id);
		List<CreditCardStatement> list = creditCardStatementService.findAll().stream()
				.filter(obj -> obj.getCard().getAccount().getId().equals(id)).toList();
		if (list.size() > 0) return ResponseEntity.ok().body(list);
		return ResponseEntity.notFound().build();
	}

	@PostMapping
	public ResponseEntity<Account> insert(@RequestBody Account obj) {
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
//	public ResponseEntity<Account> update(@PathVariable Long id, @RequestBody Account obj) {
//		obj = service.update(id, obj);
//		return ResponseEntity.ok().body(obj);
//	}
}
