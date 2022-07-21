package com.vili.sorsfinance.api.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.vili.sorsfinance.api.repositories.StateRepository;
import com.vili.sorsfinance.api.validation.constraints.ValidState;

public class StateValidator implements ConstraintValidator<ValidState, String> {

	@Autowired
	StateRepository repo;

	@Override
	public void initialize(ValidState ann) {
	}

	@Override
	public boolean isValid(String name, ConstraintValidatorContext context) {
		List<String> list = new ArrayList<>();

		if (name != null) {
			if (!repo.findByNameIgnoreCase(name).isEmpty())
				list.add("State '" + name + "' already exists");
		}

		for (String msg : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(msg).addConstraintViolation();
		}

		return list.isEmpty();
	}
}
