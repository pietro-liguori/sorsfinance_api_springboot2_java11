package com.vili.sorsfinance.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.vili.sorsfinance.entities.Contact;
import com.vili.sorsfinance.repositories.ContactRepository;
import com.vili.sorsfinance.services.exceptions.DatabaseException;
import com.vili.sorsfinance.services.exceptions.ResourceNotFoundException;

@Service
public class ContactService {

	@Autowired
	ContactRepository repository;

	public List<Contact> findAll() {
		return repository.findAll();
	}

	public Contact findById(Long id) {
		Optional<Contact> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}

	public Contact insert(Contact obj) {
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

//	public Contact update(Long id, Contact obj) {
//		try {
//			Contact entity = repository.getById(id);
//			updateData(entity, obj);
//			return repository.save(entity);
//		} catch (EntityNotFoundException e) {
//			throw new ResourceNotFoundException(id);
//		}
//	}
//
//	private void updateData(Contact entity, Contact obj) {
//		entity.setName(obj.getName());
//		entity.setEmail(obj.getEmail());
//		entity.setPhone(obj.getPhone());
//	}
}
