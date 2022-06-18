package com.vili.sorsfinance.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.vili.sorsfinance.api.entities.Contact;
import com.vili.sorsfinance.api.repositories.ContactRepository;
import com.vili.sorsfinance.validation.constraints.ValidContactList;

public class ContactListValidator implements ConstraintValidator<ValidContactList, Set<Long>> {

	@Autowired
	ContactRepository repo;

	@Override
	public void initialize(ValidContactList ann) {
	}

	@Override
	public boolean isValid(Set<Long> contacts, ConstraintValidatorContext context) {
		List<String> list = new ArrayList<>();

		int i = 0;
		for (Long contactId : contacts) {
			if (contactId == null) {
				list.add("Element contactIds[" + i + "] - Must not be null");
			} else {
				Optional<Contact> aux = repo.findById(contactId);

				if (aux.isEmpty())
					list.add("Element contactIds[" + i + "] - Resource not found: " + contactId);
			}
			i++;
		}

		for (String msg : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(msg).addConstraintViolation();
		}

		return list.isEmpty();
	}
}
