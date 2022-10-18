package com.vili.sorsfinance.framework.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;

import com.vili.sorsfinance.framework.IEntity;
import com.vili.sorsfinance.framework.exceptions.custom.ResourceNotFoundException;
import com.vili.sorsfinance.framework.request.Request;
import com.vili.sorsfinance.framework.utils.RepositoryProvider;

public interface SearchService extends RepositoryProvider {

	default List<IEntity> findAll() {
		return getRepository().findAll();
	}

	default IEntity findById(Long id) {
		Optional<IEntity> entity = getRepository().findById(id);
		return entity.orElseThrow(() -> new ResourceNotFoundException(id));
	}
	
	default Page<IEntity> findPage(Request request) {
		Specification<IEntity> spec = request.getSpecification();

		if (spec == null)
			return getRepository().findAll(request.getPageRequest());
		
		return getRepository().findAll(spec, request.getPageRequest());
	}
}
