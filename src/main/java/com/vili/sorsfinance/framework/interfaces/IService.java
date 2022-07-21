package com.vili.sorsfinance.framework.interfaces;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;

import com.vili.sorsfinance.framework.RepositoryProvider;
import com.vili.sorsfinance.framework.Request;
import com.vili.sorsfinance.framework.annotations.EntityRef;
import com.vili.sorsfinance.framework.annotations.RepositoryRef;
import com.vili.sorsfinance.framework.exceptions.DatabaseException;
import com.vili.sorsfinance.framework.exceptions.MissingAnnotationException;
import com.vili.sorsfinance.framework.exceptions.NoSuchEntityException;
import com.vili.sorsfinance.framework.exceptions.ResourceNotFoundException;

public interface IService {

	default IRepository<IEntity> getRepository() {
		if (this.getClass().isAnnotationPresent(EntityRef.class)) {
			Class<?> entityClass = this.getClass().getAnnotation(EntityRef.class).value();
			
			if (entityClass.isAnnotationPresent(RepositoryRef.class)) {
				Class<? extends IRepository<?>> repositoryClass = entityClass.getAnnotation(RepositoryRef.class).value();
				String aux = repositoryClass.getSimpleName();
				String bean = aux.substring(0, 1).toLowerCase() + aux.substring(1);
				return RepositoryProvider.getRepository(bean);
			} else {
				throw new MissingAnnotationException("Class '" + entityClass.getSimpleName() + "' not annotaded with @RepositoryRef");
			}
		} else {
			throw new MissingAnnotationException("Class '" + this.getClass().getSimpleName() + "' not annotaded with @EntityRef");
		}
	}

	default boolean existsById(Long id) {
		return getRepository().existsById(id);
	}

	default List<IEntity> findAll(Request request) {
		Specification<IEntity> spec = request.getSpecification();
		
		if (spec == null)
			return getRepository().findAll();
		
		return getRepository().findAll(request.getSpecification());
	}

	default Page<IEntity> findPage(Request request) {
		Specification<IEntity> spec = request.getSpecification();
		return getRepository().findAll(spec == null ? Specification.where(null) : spec, request.getPageRequest());
	}

	default IEntity findById(Long id) {
		Optional<IEntity> entity = getRepository().findById(id);
		return entity.orElseThrow(() -> new ResourceNotFoundException(id));
	}

	default IEntity save(IEntity entity) {
		if (entity == null)
			throw new NoSuchEntityException("Entity can't be null");
		
		entity.setUpdatedAt(new java.sql.Date(new Date().toInstant().toEpochMilli()));

		try {
			getRepository().findById(entity.getId()).ifPresent(self -> update(self, entity));
		} catch (Exception e) {
			entity.setCreatedAt(new java.sql.Date(new Date().toInstant().toEpochMilli()));
		}
		
		return getRepository().save(entity);
	}

	default void delete(Long id) {
		try {
			getRepository().deleteById(id);

		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}
	
	default void update(IEntity oldEntity, IEntity newEntity) {
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
	}
}
