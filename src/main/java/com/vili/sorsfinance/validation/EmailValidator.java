package com.vili.sorsfinance.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.vili.sorsfinance.api.repositories.EmailRepository;
import com.vili.sorsfinance.validation.constraints.ValidEmail;

public class EmailValidator implements ConstraintValidator<ValidEmail, String> {

	@Autowired
	EmailRepository repo;

	@Override
	public void initialize(ValidEmail ann) {
	}

	@Override
	public boolean isValid(String email, ConstraintValidatorContext context) {
		List<String> list = new ArrayList<>();

		if (email != null) {
			if (!repo.findByEmailIgnoreCase(email).isEmpty())
				list.add("Email '" + email + "' already in use");
		}

		for (String msg : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(msg).addConstraintViolation();
		}

		return list.isEmpty();
	}
}
