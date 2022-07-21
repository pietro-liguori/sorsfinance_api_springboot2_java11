package com.vili.sorsfinance.api.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.vili.sorsfinance.api.domain.State;
import com.vili.sorsfinance.api.repositories.StateRepository;
import com.vili.sorsfinance.api.validation.constraints.ValidStateId;

public class StateIdValidator implements ConstraintValidator<ValidStateId, Long> {

	@Autowired
	StateRepository repo;

	@Override
	public void initialize(ValidStateId ann) {
	}

	@Override
	public boolean isValid(Long id, ConstraintValidatorContext context) {
		List<String> list = new ArrayList<>();

		if (id != null) {
			Optional<State> aux = repo.findById(id);

			if (aux.isEmpty())
				list.add("State not found: " + id);
		}

		for (String msg : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(msg).addConstraintViolation();
		}

		return list.isEmpty();
	}
}
