package com.vili.sorsfinance.api.resources;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.vili.sorsfinance.api.entities.Category;
import com.vili.sorsfinance.api.entities.dto.CategoryDTO;
import com.vili.sorsfinance.api.framework.DefaultResource;

@RestController
@RequestMapping(value = "/categories")
public class CategoryResource extends DefaultResource<Category, CategoryDTO> {

	@Override
	@PostMapping
	public ResponseEntity<Category> insert(@Valid @RequestBody CategoryDTO dto) {
		Category obj = Category.fromDTO(dto);
		obj = service.save(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(obj);
	}
}
