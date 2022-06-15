package com.vili.sorsfinance.api.entities.filters;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.vili.sorsfinance.api.entities.Email;
import com.vili.sorsfinance.api.framework.EntityFilter;
import com.vili.sorsfinance.api.framework.Request;
import com.vili.sorsfinance.api.repositories.EmailRepository;

public class EmailFilter extends EntityFilter<Email> {

	public EmailFilter(Request request, EmailRepository repository) {
		super(request, repository);
	}

	@Override
	public Page<Email> apply() {
		Map<String, ?> filter = getFilter();
		Pageable pageable = getPageRequest();
		EmailRepository repository = (EmailRepository) getRepository();
		
		if (filter != null) {
			boolean hasOwner = filter.get("owner") != null;
			boolean hasPreferred = filter.get("preferred") != null;

			if (hasOwner && hasPreferred) {
				Long owner = (Long) filter.get("owner");
				Boolean preferred = (Boolean) filter.get("preferred");
				return repository.findByContactOwnerIdAndPreferred(owner, preferred, pageable);
			}

			if (hasOwner && !hasPreferred) {
				Long owner = (Long) filter.get("owner");
				return repository.findByContactOwnerId(owner, pageable);
			}

			if (!hasOwner && hasPreferred) {
				Boolean preferred = (Boolean) filter.get("preferred");
				return repository.findByPreferred(preferred, pageable);
			}
		}

		return repository.findAll(pageable);
	}
}
