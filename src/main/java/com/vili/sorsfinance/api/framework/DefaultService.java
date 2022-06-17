package com.vili.sorsfinance.api.framework;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.vili.sorsfinance.api.exceptions.DatabaseException;
import com.vili.sorsfinance.api.exceptions.ResourceNotFoundException;

public class DefaultService<T extends BusEntity> {

	@Autowired
	JpaRepository<T, Long> repository;

	public boolean existsById(Long id) {
		return repository.existsById(id);
	}

	public List<T> findAll() {
		return repository.findAll();
	}

	public Page<T> findPage() {
		return repository.findAll(Pageable.unpaged());
	}

	public Page<T> findPage(EntityFilter<T> filter) {
		if (filter != null) {
			Page<T> obj = filter.apply();
			
			if (obj != null)
				return obj;
		}
		
		return repository.findAll(filter.getPageRequest());
	}

	public T findById(Long id) {
		Optional<T> entity = repository.findById(id);
		return entity.orElseThrow(() -> new ResourceNotFoundException(id));
	}

	public T save(T entity) {
		try {
			Optional<T> aux = repository.findById(entity.getId());
			
			if (aux.isPresent()) {
				try {
					entity.setUpdatedAt(new java.sql.Date(new Date().toInstant().toEpochMilli()));
					return repository.save(update(aux.get(), entity));
				} catch (EntityNotFoundException | NoSuchElementException e) {
					throw new ResourceNotFoundException(entity.getId());
				}
			} else {
				entity.setCreatedAt(new java.sql.Date(new Date().toInstant().toEpochMilli()));
				return repository.save(entity);
			}
		} catch (Exception e) {
			entity.setCreatedAt(new java.sql.Date(new Date().toInstant().toEpochMilli()));
			return repository.save(entity);
		}
	}

	public List<T> saveAll(List<T> entities) {
		entities.forEach(ent -> ent.setCreatedAt(new java.sql.Date(new Date().toInstant().toEpochMilli())));
		return repository.saveAll(entities);
	}

	public void delete(Long id) {
		try {
			repository.deleteById(id);

		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}

	private T update(T oldEntity, T newEntity) {
		for (Field field : newEntity.getClass().getDeclaredFields()) {
			try {
				Object oldValue = field.get(oldEntity);
				Object newValue = field.get(newEntity);

				if (newValue.equals(oldValue))
					field.set(oldEntity, newValue);
			} catch (IllegalArgumentException e) {
				// TODO
			} catch (IllegalAccessException e) {
				// TODO
			} catch (SecurityException e) {
				// TODO
			}
		}
		
		return oldEntity;
	}
}
