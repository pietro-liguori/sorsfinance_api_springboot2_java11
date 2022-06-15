package com.vili.sorsfinance.api.entities.filters;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.vili.sorsfinance.api.entities.CreditCardStatement;
import com.vili.sorsfinance.api.framework.EntityFilter;
import com.vili.sorsfinance.api.framework.Request;
import com.vili.sorsfinance.api.repositories.CreditCardStatementRepository;

public class CreditCardStatementFilter extends EntityFilter<CreditCardStatement> {

	public CreditCardStatementFilter(Request request, CreditCardStatementRepository repository) {
		super(request, repository);
	}

	@Override
	public Page<CreditCardStatement> apply() {
		Map<String, ?> filter = getFilter();
		Pageable pageable = getPageRequest();
		CreditCardStatementRepository repository = (CreditCardStatementRepository) getRepository();
		
		if (filter != null) {
			boolean hasStatus = filter.get("status") != null;

			if (hasStatus) {
				Integer status = (Integer) filter.get("status");
				return repository.findByStatus(status, pageable);
			}
		}

		return repository.findAll(pageable);
	}
}
