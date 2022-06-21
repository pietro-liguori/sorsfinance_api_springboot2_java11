package com.vili.sorsfinance.api.resources;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.vili.sorsfinance.api.entities.Branch;
import com.vili.sorsfinance.api.entities.dto.BranchDTO;
import com.vili.sorsfinance.api.framework.DefaultResource;

@RestController
@RequestMapping(value = "/branches")
public class BranchResource extends DefaultResource<Branch, BranchDTO> {

	@Override
	@PostMapping
	public ResponseEntity<Branch> insert(@Valid @RequestBody BranchDTO dto) {
		Branch obj = Branch.fromDTO(dto);
		obj = service.save(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(obj);
	}
}
