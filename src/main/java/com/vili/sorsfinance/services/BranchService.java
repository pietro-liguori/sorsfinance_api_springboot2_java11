package com.vili.sorsfinance.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.vili.sorsfinance.entities.Branch;
import com.vili.sorsfinance.repositories.BranchRepository;
import com.vili.sorsfinance.services.exceptions.DatabaseException;
import com.vili.sorsfinance.services.exceptions.ResourceNotFoundException;

@Service
public class BranchService {

	@Autowired
	BranchRepository repository;

	public List<Branch> findAll() {
		return repository.findAll();
	}

	public Branch findById(Long id) {
		Optional<Branch> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}

	public Branch insert(Branch obj) {
		return repository.save(obj);
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

//	public Branch update(Long id, Branch obj) {
//		try {
//			Branch entity = repository.getById(id);
//			updateData(entity, obj);
//			return repository.save(entity);
//		} catch (EntityNotFoundException e) {
//			throw new ResourceNotFoundException(id);
//		}
//	}
//
//	private void updateData(Branch entity, Branch obj) {
//		entity.setName(obj.getName());
//		entity.setEmail(obj.getEmail());
//		entity.setPhone(obj.getPhone());
//	}
}
