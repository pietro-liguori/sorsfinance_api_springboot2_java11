package com.vili.sorsfinance.api.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;

import com.vili.sorsfinance.api.domain.Category;
import com.vili.sorsfinance.api.domain.dto.CategoryDTO;
import com.vili.sorsfinance.api.validation.constraints.ValidCategory;
import com.vili.sorsfinance.framework.DTOType;

public class CategoryValidator implements ConstraintValidator<ValidCategory, CategoryDTO> {

	@Override
	public void initialize(ValidCategory ann) {
	}

	@Override
	public boolean isValid(CategoryDTO dto, ConstraintValidatorContext context) {
		Validator validator = new Validator();

		if (dto.getMethod().equals(DTOType.INSERT)) {
			Category probe = new Category();
			probe.setName(dto.getName());
			validator.unique("name", Example.of(probe, ExampleMatcher.matching().withIgnoreCase()), true);
		} else {
			
		}

		return validator.validate(context);
	}
}
