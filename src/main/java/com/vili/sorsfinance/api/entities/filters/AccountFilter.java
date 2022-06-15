package com.vili.sorsfinance.api.entities.filters;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.vili.sorsfinance.api.entities.Account;
import com.vili.sorsfinance.api.framework.EntityFilter;
import com.vili.sorsfinance.api.framework.Request;
import com.vili.sorsfinance.api.repositories.AccountRepository;

public class AccountFilter extends EntityFilter<Account> {

	public AccountFilter(Request request, AccountRepository repository) {
		super(request, repository);
	}

	@Override
	public Page<Account> apply() {
		Map<String, ?> filter = getFilter();
		Pageable pageable = getPageRequest();
		AccountRepository repository = (AccountRepository) getRepository();
		
		if (filter != null) {
			boolean hasHolder = filter.get("holder") != null;
			boolean hasType = filter.get("type") != null;
			boolean hasStatus = filter.get("status") != null;

			if (hasHolder && hasType && hasStatus) {
				Long holder = (Long) filter.get("holder");
				Integer type = (Integer) filter.get("type");
				Integer status = (Integer) filter.get("status");
				return repository.findByHolderIdAndTypeAndStatus(holder, type, status, pageable);
			}

			if (hasHolder && hasType && !hasStatus) {
				Long holder = (Long) filter.get("holder");
				Integer type = (Integer) filter.get("type");
				return repository.findByHolderIdAndType(holder, type, pageable);
			}

			if (hasHolder && !hasType && hasStatus) {
				Long holder = (Long) filter.get("holder");
				Integer status = (Integer) filter.get("status");
				return repository.findByHolderIdAndStatus(holder, status, pageable);
			}

			if (!hasHolder && hasType && hasStatus) {
				Integer type = (Integer) filter.get("type");
				Integer status = (Integer) filter.get("status");
				return repository.findByTypeAndStatus(type, status, pageable);
			}

			if (hasHolder && !hasType && !hasStatus) {
				Long holder = (Long) filter.get("holder");
				return repository.findByHolderId(holder, pageable);
			}

			if (!hasHolder && hasType && !hasStatus) {
				Integer type = (Integer) filter.get("type");
				return repository.findByType(type, pageable);
			}

			if (!hasHolder && !hasType && hasStatus) {
				Integer status = (Integer) filter.get("status");
				return repository.findByStatus(status, pageable);
			}
		}

		return repository.findAll(pageable);
	}
}
