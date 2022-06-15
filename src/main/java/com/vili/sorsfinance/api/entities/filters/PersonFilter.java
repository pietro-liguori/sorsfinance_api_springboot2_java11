package com.vili.sorsfinance.api.entities.filters;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.vili.sorsfinance.api.entities.Person;
import com.vili.sorsfinance.api.framework.EntityFilter;
import com.vili.sorsfinance.api.framework.Request;
import com.vili.sorsfinance.api.repositories.PersonRepository;

public class PersonFilter extends EntityFilter<Person> {

	public PersonFilter(Request request, PersonRepository repository) {
		super(request, repository);
	}

	@Override
	public Page<Person> apply() {
		Map<String, ?> filter = getFilter();
		Pageable pageable = getPageRequest();
		PersonRepository repository = (PersonRepository) getRepository();
		
		if (filter != null) {
			boolean hasBranch = filter.get("branch") != null;
			boolean hasType = filter.get("type") != null;
			boolean hasProfile = filter.get("profile") != null;

			if (hasBranch && hasType && hasProfile) {
				Long branch = (Long) filter.get("branch");
				Integer type = (Integer) filter.get("type");
				Integer profile = (Integer) filter.get("profile");
				return repository.findByBranchIdAndTypeAndProfile(branch, type, profile, pageable);
			}

			if (hasBranch && hasType && !hasProfile) {
				Long branch = (Long) filter.get("branch");
				Integer type = (Integer) filter.get("type");
				return repository.findByBranchIdAndType(branch, type, pageable);
			}

			if (hasBranch && !hasType && hasProfile) {
				Long branch = (Long) filter.get("branch");
				Integer profile = (Integer) filter.get("profile");
				return repository.findByBranchIdAndProfile(branch, profile, pageable);
			}

			if (!hasBranch && hasType && hasProfile) {
				Integer type = (Integer) filter.get("type");
				Integer profile = (Integer) filter.get("profile");
				return repository.findByTypeAndProfile(type, profile, pageable);
			}

			if (hasBranch && !hasType && !hasProfile) {
				Long branch = (Long) filter.get("branch");
				return repository.findByBranchId(branch, pageable);
			}

			if (!hasBranch && hasType && !hasProfile) {
				Integer type = (Integer) filter.get("type");
				return repository.findByType(type, pageable);
			}

			if (!hasBranch && !hasType && hasProfile) {
				Integer profile = (Integer) filter.get("profile");
				return repository.findByProfile(profile, pageable);
			}
		}

		return repository.findAll(pageable);
	}
}
