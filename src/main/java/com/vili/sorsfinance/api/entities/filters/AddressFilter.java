package com.vili.sorsfinance.api.entities.filters;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.vili.sorsfinance.api.entities.Address;
import com.vili.sorsfinance.api.framework.EntityFilter;
import com.vili.sorsfinance.api.framework.Request;
import com.vili.sorsfinance.api.repositories.AddressRepository;

public class AddressFilter extends EntityFilter<Address> {

	public AddressFilter(Request request, AddressRepository repository) {
		super(request, repository);
	}

	@Override
	public Page<Address> apply() {
		Map<String, ?> filter = getFilter();
		Pageable pageable = getPageRequest();
		AddressRepository repository = (AddressRepository) getRepository();
		
		if (filter != null) {
			boolean hasOwner = filter.get("owner") != null;
			boolean hasCity = filter.get("city") != null;
			boolean hasState = filter.get("state") != null;
			boolean hasCountry = filter.get("country") != null;
			boolean hasPreferred = filter.get("preferred") != null;

			if (!hasOwner) {
				if (!hasPreferred) {
					if (hasCity) {
						Long city = (Long) filter.get("city");
						return repository.findByCityId(city, pageable);
					}

					if (!hasCity && hasState) {
						Long state = (Long) filter.get("state");
						return repository.findByCityStateId(state, pageable);
					}

					if (!hasCity && !hasState && hasCountry) {
						Long country = (Long) filter.get("country");
						return repository.findByCityStateCountryId(country, pageable);
					}
				} else {
					Boolean preferred = (Boolean) filter.get("preferred");
					
					if (hasCity) {
						Long city = (Long) filter.get("city");
						return repository.findByCityIdAndPreferred(city, preferred, pageable);
					}

					if (!hasCity && hasState) {
						Long state = (Long) filter.get("state");
						return repository.findByCityStateIdAndPreferred(state, preferred, pageable);
					}

					if (!hasCity && !hasState && hasCountry) {
						Long country = (Long) filter.get("country");
						return repository.findByCityStateCountryIdAndPreferred(country, preferred, pageable);
					}

					return repository.findByPreferred(preferred, pageable);
				}
			} else {
				Long owner = (Long) filter.get("owner");

				if (!hasPreferred) {
					if (hasCity) {
						Long city = (Long) filter.get("city");
						return repository.findByContactsOwnerIdAndCityId(owner, city, pageable);
					}

					if (!hasCity && hasState) {
						Long state = (Long) filter.get("state");
						return repository.findByContactsOwnerIdAndCityStateId(owner, state, pageable);
					}

					if (!hasCity && !hasState && hasCountry) {
						Long country = (Long) filter.get("country");
						return repository.findByContactsOwnerIdAndCityStateCountryId(owner, country, pageable);
					}
				} else {
					Boolean preferred = (Boolean) filter.get("preferred");
					
					if (hasCity) {
						Long city = (Long) filter.get("city");
						return repository.findByContactsOwnerIdAndCityIdAndPreferred(owner, city, preferred, pageable);
					}

					if (!hasCity && hasState) {
						Long state = (Long) filter.get("state");
						return repository.findByContactsOwnerIdAndCityStateCountryIdAndPreferred(owner, state, preferred, pageable);
					}

					if (!hasCity && !hasState && hasCountry) {
						Long country = (Long) filter.get("country");
						return repository.findByContactsOwnerIdAndCityStateCountryIdAndPreferred(owner, country, preferred, pageable);
					}

					return repository.findByContactsOwnerIdAndPreferred(owner, preferred, pageable);
				}

				return repository.findByContactsOwnerId(owner, pageable);
			}
		}

		return repository.findAll(pageable);
	}
}
