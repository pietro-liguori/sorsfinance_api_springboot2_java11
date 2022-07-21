package com.vili.sorsfinance.framework.interfaces;

import java.net.URI;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.vili.sorsfinance.framework.Request;
import com.vili.sorsfinance.framework.ServiceProvider;
import com.vili.sorsfinance.framework.annotations.EntityRef;
import com.vili.sorsfinance.framework.annotations.ServiceRef;
import com.vili.sorsfinance.framework.exceptions.InvalidRequestParamException;
import com.vili.sorsfinance.framework.exceptions.MissingAnnotationException;

public interface IResource<T extends IDataTransferObject> {

	default IService getService() {
		if (this.getClass().isAnnotationPresent(EntityRef.class)) {
			Class<? extends IEntity> entityClass = this.getClass().getAnnotation(EntityRef.class).value();
			
			if (entityClass.isAnnotationPresent(ServiceRef.class)) {
				Class<? extends IService> serviceClass = entityClass.getAnnotation(ServiceRef.class).value();
				String aux = serviceClass.getSimpleName();
				String bean = aux.substring(0, 1).toLowerCase() + aux.substring(1);
				return ServiceProvider.getService(bean);
			} else {
				throw new MissingAnnotationException("Class '" + entityClass.getSimpleName() + "' not annotaded with @ServiceRef");
			}
		} else {
			throw new MissingAnnotationException("Class '" + this.getClass().getSimpleName() + "' not annotaded with @EntityRef");
		}
	}

	@GetMapping
	default ResponseEntity<List<IEntity>> findAll(HttpServletRequest request) {
		if (this.getClass().isAnnotationPresent(EntityRef.class)) {
			Class<? extends IEntity> entityClass = this.getClass().getAnnotation(EntityRef.class).value();
			List<IEntity> list = getService().findAll(new Request(request, entityClass));
			return ResponseEntity.ok().body(list);
		} else {
			throw new MissingAnnotationException("Class '" + this.getClass().getSimpleName() + "' not annotaded with @EntityRef");
		}
	}

	@GetMapping(value = "/page/{page}")
	default ResponseEntity<Page<IEntity>> findPage(HttpServletRequest request) {
		if (this.getClass().isAnnotationPresent(EntityRef.class)) {
			Class<? extends IEntity> entityClass = this.getClass().getAnnotation(EntityRef.class).value();
			Page<IEntity> obj = getService().findPage(new Request(request, entityClass));
			return ResponseEntity.ok().body(obj);
		} else {
			throw new MissingAnnotationException("Class '" + this.getClass().getSimpleName() + "' not annotaded with @EntityRef");
		}		
	}

	@GetMapping(value = "/{id}")
	default ResponseEntity<IEntity> findById(@PathVariable Object id, HttpServletRequest request) {
		Long aux = null;

		try {
			aux = Long.parseLong((String) id);
		} catch (NumberFormatException e) {
			if (((String) id).equals("page"))
				throw new InvalidRequestParamException("Must inform a page number");
			else 
				throw new InvalidRequestParamException("Path variable 'id' must be a whole number");
		}
		
		IEntity obj = getService().findById(aux);
		return ResponseEntity.ok().body(obj);
	}

	@PostMapping
	default ResponseEntity<IEntity> insert(@Valid @RequestBody T dto) {
		IEntity obj = dto.toEntity();
		obj = getService().save(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(obj);
	}

	@DeleteMapping
	default	ResponseEntity<Void> delete(@PathVariable Object id) {
		Long aux = null;

		try {
			aux = Long.parseLong((String) id);
		} catch (NumberFormatException e) {
			if (((String) id).equals("page"))
				throw new InvalidRequestParamException("Must inform a page number");
			else 
				throw new InvalidRequestParamException("Path variable 'id' must be a whole number");
		}

		getService().delete(aux);
		return ResponseEntity.noContent().build();
	}

	@PutMapping
	default ResponseEntity<IEntity> update(@Valid @RequestBody T dto) {
		IEntity obj = dto.toEntity();
		obj = getService().save(obj);
		return ResponseEntity.ok().body(obj);
	}
}