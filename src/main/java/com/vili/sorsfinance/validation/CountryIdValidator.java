package com.vili.sorsfinance.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.vili.sorsfinance.api.entities.Country;
import com.vili.sorsfinance.api.repositories.CountryRepository;
import com.vili.sorsfinance.validation.constraints.ValidCountryId;

public class CountryIdValidator implements ConstraintValidator<ValidCountryId, Long> {

	@Autowired
	CountryRepository repo;

	@Override
	public void initialize(ValidCountryId ann) {
	}

	@Override
	public boolean isValid(Long id, ConstraintValidatorContext context) {
		List<String> list = new ArrayList<>();

		if (id != null) {
			Optional<Country> aux = repo.findById(id);

			if (aux.isEmpty())
				list.add("Country not found: " + id);
		}

		for (String msg : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(msg).addConstraintViolation();
		}

		return list.isEmpty();
	}
}
