package com.vili.sorsfinance.api.entities.filters;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.vili.sorsfinance.api.entities.Asset;
import com.vili.sorsfinance.api.framework.EntityFilter;
import com.vili.sorsfinance.api.framework.Request;
import com.vili.sorsfinance.api.repositories.AssetRepository;

public class AssetFilter extends EntityFilter<Asset> {

	public AssetFilter(Request request, AssetRepository repository) {
		super(request, repository);
	}

	@Override
	public Page<Asset> apply() {
		Map<String, ?> filter = getFilter();
		Pageable pageable = getPageRequest();
		AssetRepository repository = (AssetRepository) getRepository();
		
		if (filter != null) {
			boolean hasCategory = filter.get("category") != null;
			boolean hasType = filter.get("type") != null;
			if (hasCategory && hasType) {
				Long category = (Long) filter.get("category");
				Integer type = (Integer) filter.get("type");
				return repository.findByCategoriesIdAndType(category, type, pageable);
			}

			if (hasCategory && !hasType) {
				Long category = (Long) filter.get("category");
				return repository.findByCategoriesId(category, pageable);
			}

			if (!hasCategory && hasType) {
				Integer type = (Integer) filter.get("type");
				return repository.findByType(type, pageable);
			}
		}

		return repository.findAll(pageable);
	}
}
