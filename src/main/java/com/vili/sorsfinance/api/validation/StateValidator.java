package com.vili.sorsfinance.api.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;

import com.vili.sorsfinance.api.domain.Country;
import com.vili.sorsfinance.api.domain.State;
import com.vili.sorsfinance.api.domain.dto.StateDTO;
import com.vili.sorsfinance.api.repositories.StateRepository;
import com.vili.sorsfinance.api.validation.constraints.ValidState;
import com.vili.sorsfinance.framework.DTOType;

public class StateValidator implements ConstraintValidator<ValidState, StateDTO> {

	@Autowired
	StateRepository stateRepo;

	@Override
	public void initialize(ValidState ann) {
	}

	@Override
	public boolean isValid(StateDTO dto, ConstraintValidatorContext context) {
		Validator validator = new Validator();

		if (dto.getMethod().equals(DTOType.INSERT)) {
			Country country = (Country) validator.entityId("countryId", dto.getCountryId(), Country.class, true);
			
			if (country != null) {
				State probe = new State();
				probe.setName(dto.getName());
				probe.setCountry(country);
				validator.unique("name", Example.of(probe, ExampleMatcher.matching().withIgnoreCase()), true);
				probe.setName(null);;
				probe.setAcronym(dto.getAcronym());
				validator.unique("acronym", Example.of(probe, ExampleMatcher.matching().withIgnoreCase()), true);
			}
		} else {
			
		}

		return validator.validate(context);
	}
}
