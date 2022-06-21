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

import com.vili.sorsfinance.api.entities.Card;
import com.vili.sorsfinance.api.entities.CreditCard;
import com.vili.sorsfinance.api.entities.dto.CardDTO;
import com.vili.sorsfinance.api.entities.enums.CardType;
import com.vili.sorsfinance.api.entities.filters.CardFilter;
import com.vili.sorsfinance.api.framework.DefaultResource;
import com.vili.sorsfinance.api.framework.EntityFilter;
import com.vili.sorsfinance.api.framework.Request;
import com.vili.sorsfinance.api.repositories.CardRepository;

@RestController
@RequestMapping(value = "/cards")
public class CardResource extends DefaultResource<Card, CardDTO> {

	@Autowired
	CardRepository repo;

	@Override
	@SuppressWarnings("unchecked")
	protected <U extends EntityFilter<Card>> U getFilter() {
		return (U) new CardFilter(Request.from(getRequest()), repo);
	}
	
	@Override
	@PostMapping
	public ResponseEntity<Card> insert(@Valid @RequestBody CardDTO dto) {
		CardType type = CardType.toEnum(dto.getType());
		Card obj = null;

		if (Card.CREDIT_CARD_TYPES.contains(type))
			obj = service.save(CreditCard.fromDTO(dto));
		else
			obj = service.save(Card.fromDTO(dto));

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(obj);
	}
}
