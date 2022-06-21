package com.vili.sorsfinance.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.vili.sorsfinance.api.repositories.CountryRepository;
import com.vili.sorsfinance.validation.constraints.ValidCountry;

public class CountryValidator implements ConstraintValidator<ValidCountry, String> {

	@Autowired
	CountryRepository repo;

	@Override
	public void initialize(ValidCountry ann) {
	}

	@Override
	public boolean isValid(String name, ConstraintValidatorContext context) {
		List<String> list = new ArrayList<>();

		if (name != null) {
			if (!repo.findByNameIgnoreCase(name).isEmpty())
				list.add("Country '" + name + "' already exists");
		}

		for (String msg : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(msg).addConstraintViolation();
		}

		return list.isEmpty();
	}
}
