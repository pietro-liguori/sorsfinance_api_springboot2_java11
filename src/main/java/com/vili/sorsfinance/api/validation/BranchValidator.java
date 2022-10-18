package com.vili.sorsfinance.api.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;

import com.vili.sorsfinance.api.domain.Branch;
import com.vili.sorsfinance.api.domain.dto.BranchDTO;
import com.vili.sorsfinance.api.validation.constraints.ValidBranch;
import com.vili.sorsfinance.framework.DTOType;

public class BranchValidator implements ConstraintValidator<ValidBranch, BranchDTO> {

	@Override
	public void initialize(ValidBranch ann) {
	}

	@Override
	public boolean isValid(BranchDTO dto, ConstraintValidatorContext context) {
		Validator validator = new Validator();

		if (dto.getMethod().equals(DTOType.INSERT)) {
			Branch probe = new Branch();
			probe.setName(dto.getName());
			validator.unique("name", Example.of(probe, ExampleMatcher.matching().withIgnoreCase()), true);
		} else {
			
		}

		return validator.validate(context);
	}
}
