package com.vili.sorsfinance.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.vili.sorsfinance.api.entities.Contact;
import com.vili.sorsfinance.api.repositories.ContactRepository;
import com.vili.sorsfinance.validation.constraints.ValidContactId;

public class ContactIdValidator implements ConstraintValidator<ValidContactId, Long> {

	@Autowired
	ContactRepository repo;

	@Override
	public void initialize(ValidContactId ann) {
	}

	@Override
	public boolean isValid(Long id, ConstraintValidatorContext context) {
		List<String> list = new ArrayList<>();

		if (id != null) {
			Optional<Contact> aux = repo.findById(id);

			if (aux.isEmpty())
				list.add("Resource not found: " + id);
		}

		for (String msg : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(msg).addConstraintViolation();
		}

		return list.isEmpty();
	}
}
