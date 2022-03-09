package com.vili.sorsfinance.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.vili.sorsfinance.entities.State;
import com.vili.sorsfinance.repositories.StateRepository;
import com.vili.sorsfinance.services.exceptions.DatabaseException;
import com.vili.sorsfinance.services.exceptions.ResourceNotFoundException;

@Service
public class StateService {

	@Autowired
	StateRepository repository;

	public List<State> findAll() {
		return repository.findAll();
	}

	public State findById(Long id) {
		Optional<State> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}

	public State insert(State obj) {
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

//	public Estate update(Long id, Estate obj) {
//		try {
//			Estate entity = repository.getById(id);
//			updateData(entity, obj);
//			return repository.save(entity);
//		} catch (EntityNotFoundException e) {
//			throw new ResourceNotFoundException(id);
//		}
//	}
//
//	private void updateData(Estate entity, Estate obj) {
//		entity.setName(obj.getName());
//		entity.setEmail(obj.getEmail());
//		entity.setPhone(obj.getPhone());
//	}
}
