package com.vili.sorsfinance.framework.controllers;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.vili.sorsfinance.framework.IEntity;
import com.vili.sorsfinance.framework.annotations.EntityRef;
import com.vili.sorsfinance.framework.exceptions.FieldMessage;
import com.vili.sorsfinance.framework.exceptions.custom.InvalidRequestParamException;
import com.vili.sorsfinance.framework.exceptions.custom.MissingAnnotationException;
import com.vili.sorsfinance.framework.request.Request;
import com.vili.sorsfinance.framework.utils.ServiceProvider;

public interface SearchController extends ServiceProvider {

	@GetMapping
	default ResponseEntity<List<IEntity>> findAll() {
		List<IEntity> list = getService().findAll();
		return ResponseEntity.ok().body(list);
	}

	@GetMapping("/{id}")
	default ResponseEntity<IEntity> findById(@PathVariable Object id, HttpServletRequest request) {
		Long aux = null;

		try {
			aux = Long.parseLong((String) id);
			
			if (aux < 1) 
				throw new InvalidRequestParamException(Arrays.asList(new FieldMessage("id", "Path variable must be a positive whole number")));
		} catch (NumberFormatException e) {
			if (((String) id).equals(Request.PAGE_ATTRIBUTE))
				throw new InvalidRequestParamException(Arrays.asList(new FieldMessage(Request.PAGE_ATTRIBUTE, "Must inform a page number")));
			else 
				throw new InvalidRequestParamException(Arrays.asList(new FieldMessage("id", "Path variable must be a positive whole number")));
 		}
		
		IEntity obj = getService().findById(aux);
		return ResponseEntity.ok().body(obj);
	}
	
	@GetMapping("/page/{page}")
	default ResponseEntity<Page<IEntity>> findPage(HttpServletRequest request) {
		if (this.getClass().isAnnotationPresent(EntityRef.class)) {
			Class<? extends IEntity> entityClass = this.getClass().getAnnotation(EntityRef.class).value();
			Page<IEntity> obj = getService().findPage(new Request(request, entityClass));
			return ResponseEntity.ok().body(obj);
		} else {
			throw new MissingAnnotationException("Class '" + this.getClass().getSimpleName() + "' not annotaded with @EntityRef");
		}		
	}
}
