package com.vili.sorsfinance.api.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.vili.sorsfinance.api.repositories.CategoryRepository;
import com.vili.sorsfinance.api.validation.constraints.ValidCategory;

public class CategoryValidator implements ConstraintValidator<ValidCategory, String> {

	@Autowired
	CategoryRepository repo;

	@Override
	public void initialize(ValidCategory ann) {
	}

	@Override
	public boolean isValid(String name, ConstraintValidatorContext context) {
		List<String> list = new ArrayList<>();

		if (name != null) {
			if (!repo.findByNameIgnoreCase(name).isEmpty())
				list.add("Category '" + name + "' already exists");
		}

		for (String msg : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(msg).addConstraintViolation();
		}

		return list.isEmpty();
	}
}
