package com.vili.sorsfinance.api.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;

import com.vili.sorsfinance.api.domain.Country;
import com.vili.sorsfinance.api.domain.dto.CountryDTO;
import com.vili.sorsfinance.api.validation.constraints.ValidCountry;
import com.vili.sorsfinance.framework.DTOType;

public class CountryValidator implements ConstraintValidator<ValidCountry, CountryDTO> {

	@Override
	public void initialize(ValidCountry ann) {
	}

	@Override
	public boolean isValid(CountryDTO dto, ConstraintValidatorContext context) {
		Validator validator = new Validator();

		if (dto.getMethod().equals(DTOType.INSERT)) {
			Country probe = new Country();
			probe.setName(dto.getName());
			validator.unique("name", Example.of(probe, ExampleMatcher.matching().withIgnoreCase()), true);
			
			if (validator.size("acronym", dto.getAcronym(), 3, true)) {
				probe = new Country();
				probe.setAcronym(dto.getAcronym());
				validator.unique("acronym", Example.of(probe, ExampleMatcher.matching().withIgnoreCase()), true);
			}
		} else {
			
		}

		return validator.validate(context);
	}
}
