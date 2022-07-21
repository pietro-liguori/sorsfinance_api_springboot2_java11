package com.vili.sorsfinance.api.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.vili.sorsfinance.api.domain.Contact;
import com.vili.sorsfinance.api.repositories.ContactRepository;
import com.vili.sorsfinance.api.validation.constraints.ValidContactId;
import com.vili.sorsfinance.framework.FieldMessage;

public class ContactIdValidator implements ConstraintValidator<ValidContactId, Long> {

	@Autowired
	ContactRepository repo;
	
	private String fieldName;

	@Override
	public void initialize(ValidContactId ann) {
		setFieldName(ann.value());
	}

	@Override
	public boolean isValid(Long id, ConstraintValidatorContext context) {
		List<FieldMessage> list = validate(id, repo, fieldName);

		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addConstraintViolation();
		}

		return list.isEmpty();
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public static List<FieldMessage> validate(Long id, ContactRepository repo, String fieldName) {
		List<FieldMessage> list = new ArrayList<>();

		if (id != null) {
			Optional<Contact> aux = repo.findById(id);

			if (aux.isEmpty())
				list.add(new FieldMessage(fieldName, "Resource not found: " + id));
		} else {
			String msg = "Must not be null";
			
			if (fieldName.equals("contactIds[]"))
				msg = "Must not have null elements";
			
			list.add(new FieldMessage(fieldName, msg));
		}

		return list;
	}
}
