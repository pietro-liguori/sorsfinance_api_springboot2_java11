package com.vili.sorsfinance.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.vili.sorsfinance.api.entities.City;
import com.vili.sorsfinance.api.repositories.CityRepository;
import com.vili.sorsfinance.validation.constraints.ValidCityId;

public class CityIdValidator implements ConstraintValidator<ValidCityId, Long> {

	@Autowired
	CityRepository repo;

	@Override
	public void initialize(ValidCityId ann) {
	}

	@Override
	public boolean isValid(Long id, ConstraintValidatorContext context) {
		List<String> list = new ArrayList<>();

		if (id != null) {
			Optional<City> aux = repo.findById(id);

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
