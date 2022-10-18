package com.vili.sorsfinance.framework.controllers;

import java.net.URI;
import java.util.Arrays;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.vili.sorsfinance.framework.IDataTransferObject;
import com.vili.sorsfinance.framework.IEntity;
import com.vili.sorsfinance.framework.exceptions.FieldMessage;
import com.vili.sorsfinance.framework.exceptions.custom.InvalidRequestParamException;
import com.vili.sorsfinance.framework.utils.ServiceProvider;

public interface PersistenceController<T extends IDataTransferObject> extends ServiceProvider {

	@PostMapping
	default ResponseEntity<IEntity> insert(@Valid @RequestBody T dto) {
		IEntity obj = getService().save(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(obj);
	}

	@DeleteMapping
	default	ResponseEntity<Void> delete(@PathVariable Object id) {
		Long aux = null;

		try {
			aux = Long.parseLong((String) id);
			
			if (aux < 1) 
				throw new InvalidRequestParamException(Arrays.asList(new FieldMessage("id", "Path variable must be a positive whole number")));
		} catch (NumberFormatException e) {
			throw new InvalidRequestParamException(Arrays.asList(new FieldMessage("id", "Path variable must be a positive whole number")));
		}

		getService().delete(aux);
		return ResponseEntity.noContent().build();
	}

	@PutMapping
	default ResponseEntity<IEntity> update(@Valid @RequestBody T dto) {
		IEntity obj = getService().save(dto);
		return ResponseEntity.ok().body(obj);
	}
}
