package com.vili.sorsfinance.api.entities.filters;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.vili.sorsfinance.api.entities.Card;
import com.vili.sorsfinance.api.framework.EntityFilter;
import com.vili.sorsfinance.api.framework.Request;
import com.vili.sorsfinance.api.repositories.CardRepository;

public class CardFilter extends EntityFilter<Card> {

	public CardFilter(Request request, CardRepository repository) {
		super(request, repository);
	}

	@Override
	public Page<Card> apply() {
		Map<String, ?> filter = getFilter();
		Pageable pageable = getPageRequest();
		CardRepository repository = (CardRepository) getRepository();
		
		if (filter != null) {
			boolean hasType = filter.get("type") != null;
			boolean hasStatus = filter.get("status") != null;
			if (hasType && hasStatus) {
				Integer type = (Integer) filter.get("type");
				Integer status = (Integer) filter.get("status");
				return repository.findByTypeAndStatus(type, status, pageable);
			}

			if (hasType && !hasStatus) {
				Integer type = (Integer) filter.get("type");
				return repository.findByType(type, pageable);
			}

			if (hasType && hasStatus) {
				Integer status = (Integer) filter.get("status");
				return repository.findByStatus(status, pageable);
			}
		}

		return repository.findAll(pageable);
	}
}
