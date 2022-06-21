package com.vili.sorsfinance.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.vili.sorsfinance.api.repositories.CountryRepository;
import com.vili.sorsfinance.validation.constraints.ValidCountryAcronym;

public class CountryAcronymValidator implements ConstraintValidator<ValidCountryAcronym, String> {

	@Autowired
	CountryRepository repo;

	@Override
	public void initialize(ValidCountryAcronym ann) {
	}

	@Override
	public boolean isValid(String acronym, ConstraintValidatorContext context) {
		List<String> list = new ArrayList<>();

		if (acronym != null) {
			if (!repo.findByAcronymIgnoreCase(acronym).isEmpty())
				list.add("State '" + acronym + "' already exists");
		}

		for (String msg : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(msg).addConstraintViolation();
		}

		return list.isEmpty();
	}
}
