package com.vili.sorsfinance.api.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;

import com.vili.sorsfinance.api.domain.City;
import com.vili.sorsfinance.api.domain.State;
import com.vili.sorsfinance.api.domain.dto.CityDTO;
import com.vili.sorsfinance.api.validation.constraints.ValidCity;
import com.vili.sorsfinance.framework.DTOType;

public class CityValidator implements ConstraintValidator<ValidCity, CityDTO> {

	@Override
	public void initialize(ValidCity ann) {
	}

	@Override
	public boolean isValid(CityDTO dto, ConstraintValidatorContext context) {
		Validator validator = new Validator();

		if (dto.getMethod().equals(DTOType.INSERT)) {
			State state = (State) validator.entityId("stateId", dto.getStateId(), State.class, true);
			
			if (state != null) {
				City probe = new City();
				probe.setName(dto.getName());
				probe.setState(state);
				validator.unique("name", Example.of(probe, ExampleMatcher.matching().withIgnoreCase()), true);
			}
		} else {
			
		}

		return validator.validate(context);
	}
}
