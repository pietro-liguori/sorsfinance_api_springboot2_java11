package com.vili.sorsfinance.api.entities.filters;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.vili.sorsfinance.api.entities.Phone;
import com.vili.sorsfinance.api.framework.EntityFilter;
import com.vili.sorsfinance.api.framework.Request;
import com.vili.sorsfinance.api.repositories.PhoneRepository;

public class PhoneFilter extends EntityFilter<Phone> {

	public PhoneFilter(Request request, PhoneRepository repository) {
		super(request, repository);
	}

	@Override
	public Page<Phone> apply() {
		Map<String, ?> filter = getFilter();
		Pageable pageable = getPageRequest();
		PhoneRepository repository = (PhoneRepository) getRepository();
		
		if (filter != null) {
			boolean hasOwner = filter.get("owner") != null;
			boolean hasType = filter.get("type") != null;
			boolean hasPreferred = filter.get("preferred") != null;

			if (hasOwner && hasType && hasPreferred) {
				Long owner = (Long) filter.get("owner");
				Integer type = (Integer) filter.get("type");
				Boolean preferred = (Boolean) filter.get("preferred");
				return repository.findByContactOwnerIdAndTypeAndPreferred(owner, type, preferred, pageable);
			}

			if (hasOwner && hasType && !hasPreferred) {
				Long owner = (Long) filter.get("owner");
				Integer type = (Integer) filter.get("type");
				return repository.findByContactOwnerIdAndType(owner, type, pageable);
			}

			if (hasOwner && !hasType && hasPreferred) {
				Long owner = (Long) filter.get("owner");
				Boolean preferred = (Boolean) filter.get("preferred");
				return repository.findByContactOwnerIdAndPreferred(owner, preferred, pageable);
			}

			if (!hasOwner && hasType && hasPreferred) {
				Integer type = (Integer) filter.get("type");
				Boolean preferred = (Boolean) filter.get("preferred");
				return repository.findByTypeAndPreferred(type, preferred, pageable);
			}

			if (hasOwner && !hasType && !hasPreferred) {
				Long owner = (Long) filter.get("owner");
				return repository.findByContactOwnerId(owner, pageable);
			}

			if (!hasOwner && hasType && !hasPreferred) {
				Integer type = (Integer) filter.get("type");
				return repository.findByType(type, pageable);
			}

			if (!hasOwner && !hasType && hasPreferred) {
				Boolean preferred = (Boolean) filter.get("preferred");
				return repository.findByPreferred(preferred, pageable);
			}
		}

		return repository.findAll(pageable);
	}
}
