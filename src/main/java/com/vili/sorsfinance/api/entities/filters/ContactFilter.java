package com.vili.sorsfinance.api.entities.filters;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.vili.sorsfinance.api.entities.Contact;
import com.vili.sorsfinance.api.framework.EntityFilter;
import com.vili.sorsfinance.api.framework.Request;
import com.vili.sorsfinance.api.repositories.ContactRepository;

public class ContactFilter extends EntityFilter<Contact> {

	public ContactFilter(Request request, ContactRepository repository) {
		super(request, repository);
	}

	@Override
	public Page<Contact> apply() {
		Map<String, ?> filter = getFilter();
		Pageable pageable = getPageRequest();
		ContactRepository repository = (ContactRepository) getRepository();
		
		if (filter != null) {
			boolean hasOwner = filter.get("owner") != null;
			boolean hasPreferredContact = filter.get("preferredContact") != null;
			if (hasOwner && hasPreferredContact) {
				Long owner = (Long) filter.get("owner");
				Integer preferredContact = (Integer) filter.get("preferredContact");
				return repository.findByOwnerIdAndPreferredContact(owner, preferredContact, pageable);
			}

			if (hasOwner && !hasPreferredContact) {
				Long owner = (Long) filter.get("owner");
				return repository.findByOwnerId(owner, pageable);
			}

			if (!hasOwner && hasPreferredContact) {
				Integer preferredContact = (Integer) filter.get("preferredContact");
				return repository.findByPreferredContact(preferredContact, pageable);
			}
		}

		return repository.findAll(pageable);
	}
}
