package com.vili.sorsfinance.api.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vili.sorsfinance.api.entities.Card;
import com.vili.sorsfinance.api.entities.filters.CardFilter;
import com.vili.sorsfinance.api.framework.DTO;
import com.vili.sorsfinance.api.framework.DefaultResource;
import com.vili.sorsfinance.api.framework.EntityFilter;
import com.vili.sorsfinance.api.framework.Request;
import com.vili.sorsfinance.api.repositories.CardRepository;

@RestController
@RequestMapping(value = "/cards")
public class CardResource extends DefaultResource<Card, DTO<Card>> {

	@Autowired
	CardRepository repo;

	@Override
	@SuppressWarnings("unchecked")
	protected <U extends EntityFilter<Card>> U getFilter() {
		return (U) new CardFilter(Request.from(getRequest()), repo);
	}
}
