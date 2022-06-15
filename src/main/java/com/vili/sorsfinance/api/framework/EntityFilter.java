package com.vili.sorsfinance.api.framework;

import java.util.Map;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public class EntityFilter<T extends BusEntity> implements DefaultFilter<T>{

	private Map<String, ?> filter;
	private Pageable pageRequest;
	private JpaRepository<T, Long> repository;
	
	public EntityFilter(Request request, JpaRepository<T, Long> repository) {
		filter = request.getFilter();
		pageRequest = request.getPageRequest();
		this.repository = repository;
	}

	public Map<String, ?> getFilter() {
		return filter;
	}

	public Pageable getPageRequest() {
		return pageRequest;
	}

	public JpaRepository<T, Long> getRepository() {
		return repository;
	}
	
	public static <S extends BusEntity> EntityFilter<S> empty() {
		return new EntityFilter<S>(Request.from(null, null, null, Pageable.unpaged(), null), null);
	}
	
	public static <S extends BusEntity> EntityFilter<S> paging(Request request) {
		return new EntityFilter<S>(request, null);
	}
}
