package com.vili.sorsfinance.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.vili.sorsfinance.entities.Country;
import com.vili.sorsfinance.repositories.CountryRepository;
import com.vili.sorsfinance.services.exceptions.DatabaseException;
import com.vili.sorsfinance.services.exceptions.ResourceNotFoundException;

@Service
public class CountryService {

	@Autowired
	CountryRepository repository;

	public List<Country> findAll() {
		return repository.findAll();
	}

	public Country findById(Long id) {
		Optional<Country> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}

	public Country insert(Country obj) {
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

//	public Country update(Long id, Country obj) {
//		try {
//			Country entity = repository.getById(id);
//			updateData(entity, obj);
//			return repository.save(entity);
//		} catch (EntityNotFoundException e) {
//			throw new ResourceNotFoundException(id);
//		}
//	}
//
//	private void updateData(Country entity, Country obj) {
//		entity.setName(obj.getName());
//		entity.setEmail(obj.getEmail());
//		entity.setPhone(obj.getPhone());
//	}
}
